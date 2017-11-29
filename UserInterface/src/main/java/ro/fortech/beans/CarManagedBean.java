package ro.fortech.beans;

import ro.fortech.entities.Car;
import ro.fortech.services.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CarManagedBean implements Serializable {
    private Car car = new Car();
    private List<Car> cars = new ArrayList<Car>();
    private List<String> brands = new ArrayList<String>();
    private double carPrice;
    private String carColor;
    @EJB
    private CarService carService;

    public String listCars() {
        cars = carService.getCars();
        return "listCars";
    }

    public String doRegister() {
        carService.register(car);
        return "mainPage?faces-redirect=true";
    }

    public String doSearch() {
        cars = carService.validateSearch(carPrice, carColor, brands);
        return "searchResult";
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
}
