package ro.fortech.services;

import ro.fortech.dao.CarDao;
import ro.fortech.entities.Car;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CarService {

    @Inject
    private CarDao carDao;

    // TODO change name of method (register), void
    public boolean validateRegister(Car car) {
        carDao.registerCar(car);
        return true;
    }
}
