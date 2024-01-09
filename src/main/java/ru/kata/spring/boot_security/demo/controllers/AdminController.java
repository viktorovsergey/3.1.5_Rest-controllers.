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
        List<Role> allRoles = roleServiceImpl.getAllRole();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("usersList", userList);
        model.addAttribute("newUser", new User());
        return "admin";
    }
    @GetMapping("admin/{id}")
    public String showUserDetails(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUser(id));
        return "edit";
    }
    @GetMapping("/admin/delete")
    public String delete(@RequestParam("id") long userId) {
        userServiceImpl.deleteUser(userId);
        return "redirect:/admin";
    }
    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("newUser") User newUser,
                             @RequestParam(value = "roles", required = false) List<String> roles) {
        List<Role> role = roles.stream()
                .map(roleName -> new Role(roleName))
                .collect(Collectors.toList());
        newUser.setRoles(role);

        userServiceImpl.add(newUser);

        return "redirect:/admin";
    }
    @PostMapping("/admin/update")
    public String updateUser (@ModelAttribute("user") User user,
                 @RequestParam(value = "roles", required = false) List<Role> roles ) {
        User existingUser = userServiceImpl.getUser(user.getId());
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(roles);
        userServiceImpl.editUser(existingUser);
     return "redirect:/admin";
  }
    }
