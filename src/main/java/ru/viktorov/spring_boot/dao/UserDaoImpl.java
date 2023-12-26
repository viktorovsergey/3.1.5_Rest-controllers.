package ru.viktorov.spring_boot.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.viktorov.spring_boot.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
    }
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User show(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void create (User user) {
        entityManager.persist(user);
    }


    @Override
    public void update(long id, User updateUser) {
        User user = entityManager.find(User.class,id);
        if (user!=null){
            user.setLastName(updateUser.getLastName());
            user.setFirstName(updateUser.getFirstName());
            user.setEmail(updateUser.getEmail());
            entityManager.merge(user);
        }

    }

    @Override
    public void delUser(long id) {
        entityManager.remove(entityManager.find(User.class,id));
    }

}
