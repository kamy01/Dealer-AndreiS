package utilities.xml_types;

import utilities.dtos.CarDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="cars")
public class Cars {
    private List<CarDto> cars;

    @XmlElement(name="car")
    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }
}
