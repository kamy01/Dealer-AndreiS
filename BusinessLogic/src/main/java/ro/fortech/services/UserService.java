package ro.fortech.services;

import ro.fortech.dao.UserDao;
import ro.fortech.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class UserService implements Serializable {

    @Inject
    private UserDao userDao;

    public boolean validate(User user) {
        User queriedUser = userDao.findUser(user.getUsername(), user.getPassword());
        return queriedUser.getUsername() != null;
    }
}
