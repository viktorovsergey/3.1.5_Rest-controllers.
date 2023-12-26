package ru.viktorov.spring_boot.dao;


import ru.viktorov.spring_boot.model.User;

import java.util.List;

public interface UserDao {


    List<User> getAllUsers();
    public  User show (long id);
    public void create  (User user);
    public void update (long id, User user);
    public void delUser(long id);
}
