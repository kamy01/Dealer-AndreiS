package ro.fortech.beans;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ro.fortech.services.CarService;
import utilities.dtos.CarDto;
import utilities.enums.ConditionStatus;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
@RequestScoped
public class XmlManagedBean {
    private UploadedFile file;
    private CarDto carDto = new CarDto();
    @EJB
    private CarService carService;
    //TODO create XmlService add logic there

    public void upload() {
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
                    carDto = getDataFromXml(element);
                    if (carDto.getRegistrationDate() != null) {
                        carService.register(carDto);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CarDto getDataFromXml(Element element) {
        CarDto carDto = new CarDto();
        try {
            carDto.setName(element.getElementsByTagName("name").item(0).getTextContent());
            carDto.setMark(element.getElementsByTagName("mark").item(0).getTextContent());
            carDto.setColor(element.getElementsByTagName("color").item(0).getTextContent());
            carDto.setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
            carDto.setCondition(ConditionStatus.NEW); //TODO
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

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
