package ro.fortech.services;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.fortech.dao.CarDao;
import ro.fortech.dao.UserDao;
import utilities.dtos.CarDto;
import utilities.dtos.UserDto;
import utilities.enums.CarColor;
import utilities.enums.ConditionStatus;
import utilities.xml_types.Cars;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
public class XmlService {

    @Inject
    private CarDao carDao;
    @Inject
    private UserDao userDao;

    public void registerCarFromXml(UploadedFile file) {
        Cars cars = getCarDataFromXmlFile(file);
        if (cars != null)
            for (CarDto carDto : cars.getCars())
                carDao.registerCar(carDto);
    }

    private Cars getCarDataFromXmlFile(UploadedFile file) {
        File xmlFile = convertToFileFromUploadedFile(file);
        try {
            if (xmlFile.getName().contains("failedFile"))
                throw new IOException("Something went wrong in copying the file");
            JAXBContext jaxbContext = JAXBContext.newInstance(Cars.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Cars) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
            return null;
        }
    }

    private File convertToFileFromUploadedFile(UploadedFile uploadedFile) {
        File xmlFile = new File("createdFile.xml");
        try {
            InputStream inputStream = uploadedFile.getInputstream();
            OutputStream out = new FileOutputStream(xmlFile);
            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            inputStream.close();
            return xmlFile;
        } catch (Exception e) {
            e.printStackTrace();
            return new File("failedFile.xml");
        }
    }

    public void uploadCarXml(UploadedFile file) {
        try {
            File xmlFile = convertToFileFromUploadedFile(file);
            if (xmlFile.getName().contains("failedFile"))
                throw new IOException("Something went wrong in copying the file");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("car");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    CarDto carDto = getDataFromCarXml(element);
                    if (carDto.getRegistrationDate() != null) {
                        carDao.registerCar(carDto);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadUserXml(UploadedFile file) {
        try {
            File xmlFile = convertToFileFromUploadedFile(file);
            if (xmlFile.getName().contains("failedFile"))
                throw new IOException("Something went wrong in copying the file");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    UserDto userDto = getDataFromUserXml(element);
                    userDao.registerUser(userDto);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserDto getDataFromUserXml(Element element) {
        UserDto userDto = new UserDto();
        userDto.setUsername(element.getElementsByTagName("username").item(0).getTextContent());
        userDto.setPassword(element.getElementsByTagName("password").item(0).getTextContent());
        return userDto;
    }

    private CarDto getDataFromCarXml(Element element) {
        CarDto carDto = new CarDto();
        try {
            carDto.setName(element.getElementsByTagName("name").item(0).getTextContent());
            carDto.setMark(element.getElementsByTagName("mark").item(0).getTextContent());
            String color = element.getElementsByTagName("color").item(0).getTextContent();
            carDto.setColor(CarColor.valueOf(color));
            carDto.setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
            String conditionStatus = element.getElementsByTagName("condition").item(0).getTextContent();
            carDto.setCondition(ConditionStatus.valueOf(conditionStatus));
            String dateFromXml = element.getElementsByTagName("registrationDate").item(0).getTextContent();
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date date = format.parse(dateFromXml);
            carDto.setRegistrationDate(date);
            return carDto;
        } catch (ParseException e) {
            e.printStackTrace();
            return carDto;
        }
    }
}
