package ro.fortech.dao;

import ro.fortech.converters.DtoToEntityConverter;
import ro.fortech.converters.EntityToDtoConverter;
import ro.fortech.entities.CarEntity;
import utilities.dtos.CarDto;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CarDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public void registerCar(CarDto carDto) {
        CarEntity carEntity = DtoToEntityConverter.convertCar(carDto);
        em.getTransaction().begin();
        em.persist(carEntity);
        em.getTransaction().commit();
    }

    public List<CarDto> getAllCars() {
        TypedQuery<CarEntity> query = em.createQuery("SELECT c from car c", CarEntity.class);
        List<CarDto> carDtoList = new ArrayList<CarDto>();
        for (CarEntity carEntity : query.getResultList()) {
            CarDto carDto = EntityToDtoConverter.convertCar(carEntity);
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    public List<CarDto> getAvailableCars() {
        TypedQuery<CarEntity> query = em.createQuery("SELECT c from car c WHERE c.sold=false", CarEntity.class);
        List<CarDto> carDtoList = new ArrayList<CarDto>();
        for (CarEntity carEntity : query.getResultList()) {
            CarDto carDto = EntityToDtoConverter.convertCar(carEntity);
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    public List<CarDto> findCarsByFilter(double price, String color, List<String> mark) {
        TypedQuery<CarEntity> query;
        String sqlStatement = "SELECT c from car c where c.price>=?1 AND c.color LIKE ?2";
        if (mark.isEmpty()) {
            query = em.createQuery(sqlStatement, CarEntity.class);
            query.setParameter(1,price);
            query.setParameter(2,color);
        }
        else {
            sqlStatement += " AND c.mark in ?3";
            query = em.createQuery(sqlStatement, CarEntity.class);
            query.setParameter(1,price);
            query.setParameter(2,color);
            query.setParameter(3,mark);
        }

        List<CarDto> carDtoList = new ArrayList<CarDto>();
        for (CarEntity carEntity : query.getResultList()) {
            CarDto carDto = EntityToDtoConverter.convertCar(carEntity);
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    public int findCarIdForName(String name) {
        try {
            Query query = em.createQuery("SELECT c.id from car c WHERE c.name=?1");
            query.setParameter(1, name);
            return (Integer) query.getSingleResult();
        }
        catch (NoResultException e) {
            return 0;
        }
    }

    public void updateSale(String name) {
        CarEntity carEntity = em.find(CarEntity.class, findCarIdForName(name));
        em.getTransaction().begin();
        carEntity.setSold(true);
        em.getTransaction().commit();
    }
}
