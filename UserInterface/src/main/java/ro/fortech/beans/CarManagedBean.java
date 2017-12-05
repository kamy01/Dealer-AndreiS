package ro.fortech.beans;

import ro.fortech.services.CarService;
import utilities.dtos.CarDto;
import utilities.enums.ConditionStatus;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class CarManagedBean implements Serializable {

    private String name;
    private String mark;
    private String color;
    private double price;
    private ConditionStatus condition;
    private Date registrationDate;
    private List<CarDto> cars;
    private List<String> brands;
    @EJB
    private CarService carService;

    @PostConstruct
    public void init() {
        cars = new ArrayList<CarDto>();
        brands = new ArrayList<String>();
    }

    public String listCars() {
        cars = carService.getCars();
        return "listCars";
    }

    public String doRegister() {
        CarDto carDto = buildCarDto();
        carService.register(carDto);
        return "mainPage?faces-redirect=true";
    }

    private CarDto buildCarDto() {
        CarDto carDto = new CarDto();
        carDto.setName(name);
        carDto.setMark(mark);
        carDto.setColor(color);
        carDto.setPrice(price);
        carDto.setCondition(condition);
        carDto.setRegistrationDate(registrationDate);
        return carDto;
    }

    public String doSearch() {
        cars = carService.validateSearch(price, color, brands);
        return "searchResult";
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getMark() { return mark; }

    public void setMark(String mark) { this.mark = mark; }

    public ConditionStatus getCondition() { return condition; }

    public void setCondition(ConditionStatus condition) { this.condition = condition; }

    public Date getRegistrationDate() { return registrationDate; }

    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
