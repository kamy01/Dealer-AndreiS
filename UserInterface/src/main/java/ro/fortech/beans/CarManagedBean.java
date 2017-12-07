package ro.fortech.beans;

import ro.fortech.services.CarService;
import utilities.dtos.CarDto;
import utilities.enums.CarBrand;
import utilities.enums.CarColor;
import utilities.enums.ConditionStatus;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private List<String> selectedMarks;
    private List<String> availableColors;
    private List<String> availableMarks;
    private List<String> conditionStatuses;
    @EJB
    private CarService carService;

    @PostConstruct
    public void init() {
        cars = new ArrayList<CarDto>();
        selectedMarks = new ArrayList<String>();
        availableColors = getCarColors();
        availableMarks = getCarMarks();
        conditionStatuses = getCarConditionStatuses();
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

    public String doSearch() {
        cars = carService.validateSearch(price, color, selectedMarks);
        return "searchResult";
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

    private List<String> getCarColors() {
        List<String> availableCarColors = new ArrayList<String>();
        for (CarColor carColor : CarColor.values()) {
            availableCarColors.add(carColor.toString());
        }
        return availableCarColors;
    }

    private List<String> getCarMarks() {
        List<String> availableCarMarks = new ArrayList<String>();
        for (CarBrand carMark : CarBrand.values()) {
            availableCarMarks.add(carMark.toString());
        }
        return availableCarMarks;
    }

    private List<String> getCarConditionStatuses() {
        List<String> availableConditionStatuses = new ArrayList<String>();
        for (ConditionStatus carConditionStatus : ConditionStatus.values()) {
            availableConditionStatuses.add(carConditionStatus.toString());
        }
        return availableConditionStatuses;
    }

    public List<String> getConditionStatuses() {
        return conditionStatuses;
    }

    public void setConditionStatuses(List<String> conditionStatuses) {
        this.conditionStatuses = conditionStatuses;
    }

    public List<String> getAvailableMarks() {
        return availableMarks;
    }

    public void setAvailableMarks(List<String> availableMarks) {
        this.availableMarks = availableMarks;
    }

    public List<String> getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(List<String> availableColors) {
        this.availableColors = availableColors;
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

    public List<String> getSelectedMarks() {
        return selectedMarks;
    }

    public void setSelectedMarks(List<String> selectedMarks) {
        this.selectedMarks = selectedMarks;
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
