package ro.fortech.services;

import ro.fortech.dao.CarDao;
import utilities.dtos.CarDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CarService {

    @Inject
    private CarDao carDao;

    public void register(CarDto car) {
        carDao.registerCar(car);
    }

    public List<CarDto> getCars() {
        return carDao.getAllCars();
    }

    public List<CarDto> getAvailableCars() {
        return carDao.getAvailableCars();
    }

    public List<CarDto> validateSearch(double price, String color, List<String> mark) {
        List<CarDto> cars;
        cars = carDao.findCarsByFilter(price, color, mark);
        return cars;
    }

    public void validateSale(String name) {
        carDao.updateSale(name);
    }
}
