package ro.fortech.dao;

import ro.fortech.entities.Car;
import utilities.dtos.CarDto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CarDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public void registerCar(CarDto carDto) {
        Car car = new Car();
        car.setName(carDto.getName());
        car.setMark(carDto.getMark());
        car.setColor(carDto.getColor());
        car.setPrice(carDto.getPrice());
        car.setCondition(carDto.getCondition());
        car.setRegistrationDate(carDto.getRegistrationDate());

        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    public List<CarDto> getAllCars() {
        TypedQuery<Car> query = em.createQuery("SELECT c from car c", Car.class);
        List<CarDto> carDtoList = new ArrayList<CarDto>();
        for (Car car : query.getResultList()) {
            CarDto carDto = new CarDto();
            carDto.setName(car.getName());
            carDto.setMark(car.getMark());
            carDto.setColor(car.getColor());
            carDto.setPrice(car.getPrice());
            carDto.setCondition(car.getCondition());
            carDto.setRegistrationDate(car.getRegistrationDate());
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    public List<CarDto> findCarsByFilter(double price, String color, List<String> mark) {
        TypedQuery<Car> query;
        String sqlStatement = "SELECT c from car c where c.price>=?1 AND c.color LIKE ?2";
        if (mark.isEmpty()) {
            query = em.createQuery(sqlStatement, Car.class);
            query.setParameter(1,price);
            query.setParameter(2,color);
        }
        else {
            sqlStatement += " AND c.mark in ?3";
            query = em.createQuery(sqlStatement, Car.class);
            query.setParameter(1,price);
            query.setParameter(2,color);
            query.setParameter(3,mark);
        }

        List<CarDto> carDtoList = new ArrayList<CarDto>();
        for (Car car : query.getResultList()) {
            CarDto carDto = new CarDto();
            carDto.setName(car.getName());
            carDto.setMark(car.getMark());
            carDto.setColor(car.getColor());
            carDto.setPrice(car.getPrice());
            carDto.setCondition(car.getCondition());
            carDto.setRegistrationDate(car.getRegistrationDate());
            carDtoList.add(carDto);
        }
        return carDtoList;
    }
}
