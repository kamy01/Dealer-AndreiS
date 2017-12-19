package ro.fortech.services;

import ro.fortech.dao.DealerDao;
import utilities.dtos.UserDto;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DealerService {

    @Inject
    private DealerDao dealerDao;

    public boolean validateRegister(UserDto userDto) {
        int userId = dealerDao.findUserIdForUserName(userDto.getUsername());
        userDto.setUserId(userId);
        if (userId > 0) {
            dealerDao.registerDealer(userDto);
            return true;
        }
        else
            return false;
    }

    public void validateSale(UserDto userDto, double carPrice) {
        dealerDao.updateAmount(userDto, carPrice);
    }
}
