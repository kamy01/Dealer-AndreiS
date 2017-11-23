package ro.fortech.services;

import ro.fortech.dao.UserDao;
import ro.fortech.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class UserService implements Serializable {

    @Inject
    private UserDao userDao;

    public boolean validateLogin(User user) {
        User queriedUser = userDao.findUser(user.getUsername(), user.getPassword());
        return queriedUser.getUsername() != null;
    }

    public boolean validateRegister(User user) {
        boolean doesUserExist = userDao.doesUserExist(user.getUsername());
        if (!doesUserExist)
        {
            userDao.registerUser(user);
            return true;
        }
        else
            return false;
    }
}
