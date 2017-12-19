package ro.fortech.converters;

import ro.fortech.entities.CarEntity;
import ro.fortech.entities.UserEntity;
import utilities.dtos.CarDto;
import utilities.dtos.UserDto;

public class EntityToDtoConverter {

    public static CarDto convertCar(CarEntity carEntity) {
        CarDto carDto = new CarDto();
        carDto.setName(carEntity.getName());
        carDto.setMark(carEntity.getMark());
        carDto.setColor(carEntity.getColor());
        carDto.setPrice(carEntity.getPrice());
        carDto.setCondition(carEntity.getCondition());
        carDto.setRegistrationDate(carEntity.getRegistrationDate());
        carDto.setSold(carEntity.isSold());
        return carDto;
    }

    public static UserDto convertUser(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        return userDto;
    }
}
