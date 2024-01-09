package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void add (User user);
    void addUser(User newUser);

    void deleteUser(long userId);

//    void editUser(long userId, User updatedUser, List<Long> roleIds);
    void editUser(User updatedUser);
    User getUser(long userId);

}

