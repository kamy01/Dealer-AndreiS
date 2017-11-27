package ro.fortech.dao;

import ro.fortech.entities.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CarDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public void registerCar(Car car) {
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    public List<Car> getAllCars() {
        TypedQuery<Car> query = em.createQuery("SELECT c from car c", Car.class);
        return query.getResultList();
    }
}
