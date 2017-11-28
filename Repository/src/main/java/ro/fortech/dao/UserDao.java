package ro.fortech.dao;

import ro.fortech.entities.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class UserDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public User findUser(String username, String password) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u from user u WHERE u.username=?1 AND u.password=?2", User.class);
            query.setParameter(1, username);
            query.setParameter(2, password);
            return query.getSingleResult();
        }
        catch (Exception e) {
            return new User();
        }
    }

    public void registerUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public boolean doesUserExist(String username) {
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
            query.setParameter(1, username);
            User user = query.getSingleResult();
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

}
