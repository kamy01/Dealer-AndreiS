package ro.fortech.services;

import ro.fortech.dao.BuyerDao;
import utilities.dtos.UserDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Random;

@Stateless
public class BuyerService {

    @Inject
    private BuyerDao buyerDao;

    public boolean validateRegister(UserDto userDto) {
        long identificationNumber = new Random().nextLong() & Long.MAX_VALUE;
        userDto.setIdentificationNumber(identificationNumber);
        int userId = buyerDao.findUserIdForUserName(userDto.getUsername());
        userDto.setUserId(userId);
        if (userId > 0) {
            buyerDao.registerBuyer(userDto);
            return true;
        }
        else
            return false;
    }
}
