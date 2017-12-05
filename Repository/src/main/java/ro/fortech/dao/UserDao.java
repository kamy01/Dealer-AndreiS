package ro.fortech.dao;

import ro.fortech.converters.DtoToEntityConverter;
import ro.fortech.converters.EntityToDtoConverter;
import ro.fortech.entities.UserEntity;
import utilities.dtos.UserDto;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class UserDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public UserDto findUser(String username, String password) {
        try {
            TypedQuery<UserEntity> query = em.createQuery("SELECT u from user u WHERE u.username=?1 AND u.password=?2", UserEntity.class);
            query.setParameter(1, username);
            query.setParameter(2, password);
            UserDto userDto = EntityToDtoConverter.convertUser(query.getSingleResult());
            return userDto;
        }
        catch (Exception e) {
            return new UserDto();
        }
    }

    public void registerUser(UserDto userDto) {
        UserEntity userEntity = DtoToEntityConverter.convertUser(userDto);
        em.getTransaction().begin();
        em.persist(userEntity);
        em.getTransaction().commit();
    }

    public boolean doesUserExist(String username) {
        try {
            TypedQuery<UserEntity> query = em.createNamedQuery("User.findByName", UserEntity.class);
            query.setParameter(1, username);
            UserDto userDto = EntityToDtoConverter.convertUser(query.getSingleResult());
            return true;
        }
        catch (NoResultException e) {
            return false;
        }
    }

}
