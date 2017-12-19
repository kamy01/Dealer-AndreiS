package ro.fortech.beans;

import ro.fortech.services.CarService;
import ro.fortech.services.DealerService;
import utilities.dtos.CarDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SellCarManagedBean implements Serializable {

    private List<CarDto> cars;
    @EJB
    private CarService carService;
    @EJB
    private DealerService dealerService;
    @Inject
    private UserManagedBean userManagedBean;

    public String doSell(String name, double carPrice) {
        carService.validateSale(name);
        dealerService.validateSale(userManagedBean.getUser(), carPrice);
        return "sellCar?faces-redirect=true";
    }

    @PostConstruct
    void init() {
        cars = new ArrayList<CarDto>();
        cars = carService.getAvailableCars();
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }
}
