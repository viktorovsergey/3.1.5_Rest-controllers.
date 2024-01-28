package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final UserServiceImpl userServiceImpl;
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, UserService userService, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/info")
    public ResponseEntity<User> userInfo(Principal principal) {
        return new ResponseEntity<>(userServiceImpl.findByName(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid User userNew) {
        userService.add(userNew);
        return new ResponseEntity<>(userNew, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.getUser(id);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
