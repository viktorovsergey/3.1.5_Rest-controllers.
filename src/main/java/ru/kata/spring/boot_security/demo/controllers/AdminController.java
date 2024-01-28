package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.exeption_handihg.NoSuchUserException;
import ru.kata.spring.boot_security.demo.service.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final UserService userService;
    private final UserServiceInterface userServiceInterface;
    private  final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, UserServiceInterface userServiceInterface, RoleService roleService) {
        this.userService = userService;
        this.userServiceInterface = userServiceInterface;
        this.roleService = roleService;
    }
    @GetMapping("/info")
    public ResponseEntity<User> userInfo (Principal principal) {
        return new ResponseEntity<>(userService.findByName(principal.getName()), HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceInterface.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
    }

//    @GetMapping("/roles/{id}")
//    public ResponseEntity<List<Role>> getRole(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(userServiceInterface.getUser(id).getRoles(), HttpStatus.OK);
//    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userServiceInterface.getUser(id), HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid User userNew) {
        userServiceInterface.add(userNew);
        return new ResponseEntity<>(userNew, HttpStatus.OK);
    }
    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        userServiceInterface.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userServiceInterface.getUser(id);
        userServiceInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
