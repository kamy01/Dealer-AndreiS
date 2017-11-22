package ro.fortech.dao;

import ro.fortech.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless
public class UserDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public User findUser(String username, String password) {
        TypedQuery<User> query;
        try {
            query = em.createQuery("SELECT u from user u WHERE u.username=?1 AND u.password=?2", User.class);
            query.setParameter(1, username);
            query.setParameter(2, password);
            return query.getSingleResult();
        }
        catch (Exception e) {
            return new User();
        }
    }
}
