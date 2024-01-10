package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserUserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private UserUserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;


    @Autowired
    public void setUserService(UserUserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    public AdminController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        List<User> userList = userServiceImpl.getAllUsers();
        model.addAttribute("usersList", userList);
        return "admin";
    }

    @GetMapping("admin/{id}")
    public String showUserDetails(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUser(id));
        return "edit";
    }
    @GetMapping("/admin/delete")
    public String delete(@RequestParam("id") long userId) {
        userServiceImpl.delete(userId);
        return "redirect:/admin";
    }
//    @PostMapping("/admin/create")
//    public String createUser(@RequestParam("name") String name,
//                             @RequestParam("age") int age,
//                             @RequestParam("email") String email,
//                             @RequestParam("password") String password,
//                             @RequestParam(value = "roles", required = false) List<String> roles) {
//
//        User user = new User();
//        user.setName(name);
//        user.setAge(age);
//        user.setEmail(email);
//        user.setPassword(password);
//        List<Role> userRoles = roles.stream()
//                .map(roleName -> new Role(roleName))
//                .collect(Collectors.toList());
//        user.setRoles(userRoles);
//        userServiceImpl.add(user);
//
//        return "redirect:/admin";
//    }
    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user
    //                         @RequestParam(value = "roles", required = false) List<Role> roles
    ) {

//        // Загружаем существующего пользователя из базы данных
//        User existingUser = userServiceImpl.getUser(user.getId());
//
//        // Обновляем данные пользователя
//        existingUser.setName(user.getName());
//        existingUser.setAge(user.getAge());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(user.getPassword());
////        existingUser.setRoles(roles);
//        userServiceImpl.update(user,int id);

     return "redirect:/admin";
  }
    }
