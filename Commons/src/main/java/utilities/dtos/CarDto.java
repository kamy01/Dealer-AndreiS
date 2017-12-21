package utilities.dtos;

import utilities.enums.CarColor;
import utilities.enums.ConditionStatus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name="car")
public class CarDto implements Serializable {

    private String name;
    private String mark;
    private CarColor color;
    private double price;
    private ConditionStatus condition;
    private Date registrationDate;
    private boolean sold;

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    @XmlElement
    public CarColor getColor() { return color; }

    public void setColor(CarColor color) { this.color = color; }

    @XmlElement
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @XmlElement
    public ConditionStatus getCondition() {
        return condition;
    }

    public void setCondition(ConditionStatus condition) {
        this.condition = condition;
    }
    @XmlElement
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    @XmlElement
    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
