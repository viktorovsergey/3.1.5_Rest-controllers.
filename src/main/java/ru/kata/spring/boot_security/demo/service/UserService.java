package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    void add (User user, List<Long> id);
    void delete(long id);
    void update(long id, User updateUser,List<Long> roleID);
    User findByUsername(String username);

    void update(User user, long id);

    User getUser(long id);
    List<User> getAllUsers();
}

