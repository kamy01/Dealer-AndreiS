package ro.fortech.dao;

import ro.fortech.converters.DtoToEntityConverter;
import ro.fortech.entities.BuyerEntity;
import utilities.dtos.UserDto;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class BuyerDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public void registerBuyer(UserDto userDto) {
        BuyerEntity buyerEntity = DtoToEntityConverter.convertBuyer(userDto);
        em.getTransaction().begin();
        em.persist(buyerEntity);
        em.getTransaction().commit();
    }

    public int findUserIdForUserName(String username) {
        try {
            Query query = em.createQuery("SELECT u.id from user u WHERE u.username=?1");
            query.setParameter(1, username);
            return (Integer) query.getSingleResult();
        }
        catch (NoResultException e) {
            return 0;
        }
    }
}
