package ro.fortech.dao;

import ro.fortech.entities.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
public class CarDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public void registerCar(Car car) {
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }
}
