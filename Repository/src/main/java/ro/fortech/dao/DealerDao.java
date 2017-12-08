package ro.fortech.dao;

import ro.fortech.converters.DtoToEntityConverter;
import ro.fortech.entities.DealerEntity;
import utilities.dtos.UserDto;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class DealerDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
    private EntityManager em = emf.createEntityManager();

    public void registerDealer(UserDto userDto) {
        DealerEntity dealerEntity = DtoToEntityConverter.convertDealer(userDto);
        em.getTransaction().begin();
        em.persist(dealerEntity);
        em.getTransaction().commit();
    }

    public int findUserIdForUserName(String username) {
        try {
            Query query = em.createQuery("SELECT u.id from user u WHERE u.username=?1");
            query.setParameter(1, username);
            return (Integer) query.getSingleResult();
        }
        catch (Exception e) {
            return 0;
        }
    }
}
