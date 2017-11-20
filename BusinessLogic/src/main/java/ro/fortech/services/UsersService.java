package ro.fortech.services;

import ro.fortech.entities.Users;

import javax.persistence.*;
import java.util.List;

public class UsersService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public UsersService() {}

    public boolean validate(Users user) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM users u", Users.class);
        List<Users> users = query.getResultList();

        for (Users myUser : users) {
            if (myUser.getUsername().equals(user.getUsername()) && myUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
