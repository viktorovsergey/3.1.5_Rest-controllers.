package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserUserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {
    private UserUserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserUserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        User user = userServiceImpl.findByUsername(principal.getName());
        model.addAttribute("userName", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("email", user.getEmail());
        return "user";
    }


}