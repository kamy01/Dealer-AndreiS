package ro.fortech.dealer;

import ro.fortech.entities.Car;
import ro.fortech.services.CarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CarManagedBean {
    private Car car = new Car();
    private List<Car> cars = new ArrayList<Car>();
    @EJB
    private CarService carService;

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

    @PostConstruct
    public void init() {
        cars = carService.getCars();
    }

    public String doRegister() {
        carService.register(car);
        return "mainPage?faces-redirect=true";
    }
}
