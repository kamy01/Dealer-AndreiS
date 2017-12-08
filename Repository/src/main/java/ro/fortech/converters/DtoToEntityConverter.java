package ro.fortech.converters;

import ro.fortech.entities.BuyerEntity;
import ro.fortech.entities.CarEntity;
import ro.fortech.entities.DealerEntity;
import ro.fortech.entities.UserEntity;
import utilities.dtos.CarDto;
import utilities.dtos.UserDto;

public class DtoToEntityConverter {

    public static CarEntity convertCar(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setName(carDto.getName());
        carEntity.setMark(carDto.getMark());
        carEntity.setColor(carDto.getColor());
        carEntity.setPrice(carDto.getPrice());
        carEntity.setCondition(carDto.getCondition());
        carEntity.setRegistrationDate(carDto.getRegistrationDate());
        return carEntity;
    }

    public static UserEntity convertUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

    public static DealerEntity convertDealer(UserDto userDto) {
        DealerEntity dealerEntity = new DealerEntity();
        dealerEntity.setFirstName(userDto.getUsername());
        dealerEntity.setLastName(userDto.getUsername() + userDto.getPassword());
        dealerEntity.setAmount(userDto.getAmount());
        dealerEntity.setUserId(userDto.getUserId());
        return dealerEntity;
    }

    public static BuyerEntity convertBuyer(UserDto userDto) {
        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setFirstName(userDto.getUsername());
        buyerEntity.setLastName(userDto.getUsername() + userDto.getPassword());
        buyerEntity.setIdentificationNumber(userDto.getIdentificationNumber());
        buyerEntity.setUserId(userDto.getUserId());
        return buyerEntity;
    }
}
