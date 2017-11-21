package ro.fortech.services;

import ro.fortech.dao.UserDao;
import ro.fortech.entities.User;

import javax.inject.Inject;

public class UserService {

    @Inject
    private UserDao userDao = new UserDao();

    public UserService() {}

    public boolean validate(User user) {
        User queriedUser = userDao.findUser(user.getUsername(), user.getPassword());
        return queriedUser != null;
    }
}
