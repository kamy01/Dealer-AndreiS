package ro.fortech.dealer;

import ro.fortech.entities.Car;
import ro.fortech.services.CarService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CarManageBean {
    private Car car = new Car();
    @EJB
    private CarService carService;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String doRegister() {
        boolean succesfullyRegistered = carService.validateRegister(car);
        if (succesfullyRegistered)
            return "success?faces-redirect=true";
        else
            return "registerCar?faces-redirect=true";
    }
}
