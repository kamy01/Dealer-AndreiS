package ro.fortech.services;

import org.primefaces.model.SortOrder;
import ro.fortech.dao.CarDao;
import utilities.dtos.CarDto;
import utilities.enums.CarColor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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

    public List<CarDto> validateSearch(double price, CarColor color, List<String> mark) {
        List<CarDto> cars;
        cars = carDao.findCarsByFilter(price, color, mark);
        return cars;
    }

    public void validateSale(String name) {
        carDao.updateSale(name);
    }

    public List<CarDto> getCarList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return carDao.getLazyCarList(first, pageSize);
    }

    public int getCarTotalCount() {
        return carDao.getCarTotalCount();
    }
}
