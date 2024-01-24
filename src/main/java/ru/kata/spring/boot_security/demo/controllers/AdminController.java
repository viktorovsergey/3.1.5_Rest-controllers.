package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.exeption_handihg.NoSuchUserException;
import ru.kata.spring.boot_security.demo.service.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    private final UserServiceInterface userServiceInterface;


    @Autowired
    public AdminController(UserService userService, UserServiceInterface userServiceInterface) {
        this.userService = userService;
        this.userServiceInterface = userServiceInterface;
    }

    @GetMapping()
    public List<User> showAllUsers() {
        return userServiceInterface.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        User user = userServiceInterface.getUser(id);
        if (user == null) {
            throw new NoSuchUserException("No user with" + id + "in Database");
        }
        return user;
    }

    @PostMapping
    public User addNewUser(@RequestBody User user ) {
        userServiceInterface.add(user);
        return user;
    }
//    @PutMapping("/{id}")
//    public User updateUser(@RequestBody User user){
//        userServiceInterface.update(user,user.getId());
//        return user;
//}

//    @GetMapping()
//    public String getAllUsers (Model model, Principal principal) {
//        model.addAttribute("allUsers", userServiceInterface.getAllUsers());
//        model.addAttribute("user", userService.findByName(principal.getName()));
//        model.addAttribute("allRoles", roleServiceInterface.getAllRole());
//        return "admin/index";
//    }

    //    @PostMapping()
//    public String add(@ModelAttribute("user") User user) {
//        userServiceInterface.add(user);
//        return "redirect:/admin";
//    }
//
    @PatchMapping("/{id}")
    public User edit(@RequestBody User user, @PathVariable("id") long id) {
        userServiceInterface.update(user, id);
        return user;
    }

//    @PostMapping("/delete")
//    public String delete(@RequestParam(value = "id") long id) {
//        userServiceInterface.delete(id);
//        return "redirect:/admin";
//    }
}
