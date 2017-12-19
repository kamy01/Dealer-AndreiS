package ro.fortech.services;

import ro.fortech.dao.UserDao;
import utilities.dtos.UserDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class UserService implements Serializable {

    @Inject
    private UserDao userDao;

    public boolean validateLogin(UserDto userDto) {
        UserDto queriedUser = userDao.findUser(userDto.getUsername(), userDto.getPassword());
        return queriedUser.getUsername() != null;
    }

    public boolean validateRegister(UserDto userDto) {
        boolean doesUserExist = userDao.doesUserExist(userDto.getUsername());
        if (!doesUserExist)
        {
            userDao.registerUser(userDto);
            return true;
        }
        else
            return false;
    }

    public boolean validateRole(UserDto userDto) {
        boolean isUserADealer = userDao.isUserADealer(userDto);
        return isUserADealer;
    }
}
