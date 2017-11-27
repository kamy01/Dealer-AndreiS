package ro.fortech.services;

import ro.fortech.dao.CarDao;
import ro.fortech.entities.Car;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CarService {

    @Inject
    private CarDao carDao;

    public void register(Car car) {
        carDao.registerCar(car);
    }

    public List<Car> getCars() {
        return carDao.getAllCars();
    }
}
