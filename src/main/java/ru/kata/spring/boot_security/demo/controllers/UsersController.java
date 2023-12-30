package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    @GetMapping("/user")
    public String showUser(Model model){

        String userName = "it is user";
        String password ="this password";
        model.addAttribute("userName", userName);
        model.addAttribute("password", password);
        return "user";
    }
    @GetMapping("/admin")
    public String showAdmin(Model model){

        String userName = "it is admin";
        String password ="this is password";
        model.addAttribute("userName", userName);
        model.addAttribute("password", password);
        return "admin";
    }
}