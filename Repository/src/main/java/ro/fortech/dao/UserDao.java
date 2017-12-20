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

    public boolean isUserADealer(UserDto userDto) {
        try {
            TypedQuery<UserEntity> query = em.createQuery("SELECT u FROM user u, dealer d WHERE u.username=?1 AND u.id = d.userId", UserEntity.class);
            query.setParameter(1, userDto.getUsername());
            String username = query.getSingleResult().getUsername();
            return username.equals(userDto.getUsername());
        }
        catch (NoResultException e) {
            return false;
        }
    }

    public int getIdFromUserName(String username) {
        try {
            Query query = em.createQuery("SELECT u.id FROM user u WHERE u.username=?1", UserEntity.class);
            query.setParameter(1, username);
            return (Integer) query.getSingleResult();
        }
        catch (NoResultException e) {
            return 0;
        }
    }

}
