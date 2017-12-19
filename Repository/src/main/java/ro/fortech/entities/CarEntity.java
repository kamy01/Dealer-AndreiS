package ro.fortech.entities;

import utilities.enums.ConditionStatus;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity(name="car")
public class CarEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mark;
    private String color;
    private double price;
    @Column(name="\"condition\"")
    @Enumerated(EnumType.STRING)
    private ConditionStatus condition;
    @Past
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    private boolean sold;

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

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
