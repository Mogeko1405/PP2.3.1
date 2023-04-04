package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.contains(getUser(id)) ? getUser(id) : entityManager.merge(getUser(id)));
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        entityManager.merge(updatedUser);
    }
}
