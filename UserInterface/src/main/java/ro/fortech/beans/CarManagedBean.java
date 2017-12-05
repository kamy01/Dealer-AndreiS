package ro.fortech.beans;

import ro.fortech.entities.Car;
import ro.fortech.services.CarService;
import utilities.dtos.CarDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CarManagedBean implements Serializable {
    private CarDto car;
    private List<CarDto> cars;
    private List<String> brands;
    private double price;
    private String color;
    @EJB
    private CarService carService;

    @PostConstruct
    public void init() {
        car = new CarDto();
        cars = new ArrayList<CarDto>();
        brands = new ArrayList<String>();
    }

    public String listCars() {
        cars = carService.getCars();
        return "listCars";
    }

    public String doRegister() {
        carService.register(car);
        return "mainPage?faces-redirect=true";
    }

    public String doSearch() {
        cars = carService.validateSearch(price, color, brands);
        return "searchResult";
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

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
