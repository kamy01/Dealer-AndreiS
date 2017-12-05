package utilities.dtos;

import utilities.enums.ConditionStatus;

import java.io.Serializable;
import java.util.Date;

public class CarDto implements Serializable {

    private String name;
    private String mark;
    private String color;
    private double price;
    private ConditionStatus condition;
    private Date registrationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ConditionStatus getCondition() {
        return condition;
    }

    public void setCondition(ConditionStatus condition) {
        this.condition = condition;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
