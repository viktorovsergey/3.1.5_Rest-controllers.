package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceInterface;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceInterface userServiceInterface;
    private final RoleService roleServiceInterface;
    private final UserService userService;


    @Autowired
    public AdminController(UserServiceInterface userServiceInterface, RoleService roleServiceInterface, UserService userService) {
        this.userServiceInterface = userServiceInterface;
        this.roleServiceInterface = roleServiceInterface;
        this.userService = userService;
    }


    @GetMapping()
    public String getAllUsers (Model model, Principal principal) {
        model.addAttribute("allUsers", userServiceInterface.getAllUsers());
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("allRoles", roleServiceInterface.getAllRole());
        return "admin/index";
    }

    @PostMapping()
    public String add (@ModelAttribute("user") User user) {
        userServiceInterface.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit (@ModelAttribute ("user") User user, @RequestParam(value = "id") long id) {
       userServiceInterface.update(user,id);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete (@RequestParam(value = "id") long id) {
        userServiceInterface.delete(id);
        return "redirect:/admin";
    }
}
