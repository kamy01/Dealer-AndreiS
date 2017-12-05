package ro.fortech.converters;

import ro.fortech.entities.CarEntity;
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
}
