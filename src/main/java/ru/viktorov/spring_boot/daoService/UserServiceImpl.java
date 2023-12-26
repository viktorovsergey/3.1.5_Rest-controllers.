package ru.viktorov.spring_boot.daoService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.viktorov.spring_boot.dao.UserDao;
import ru.viktorov.spring_boot.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

private UserDao userDao;

@Autowired
public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        userDao.update(id, user);
    }

    @Override
    @Transactional
    public void delUser(long id) {
        userDao.delUser(id);
    }
}