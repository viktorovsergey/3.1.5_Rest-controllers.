package ru.viktorov.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.viktorov.spring_boot.daoService.UserService;
import ru.viktorov.spring_boot.model.User;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String usersList(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("user/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") long userId,
                         @RequestParam("firstName") String userFirstName,
                         @RequestParam("lastName") String userLastName,
                         @RequestParam("email") String userEmail,
                         Model model) {
     model.addAttribute("user", new User(userId, userFirstName, userLastName,userEmail));
        return "update";
    }
    @PostMapping("/edit")
    public String edit(@RequestParam("id") int id, @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") long userId) {
        userService.delUser(userId);
        return "redirect:/";
    }
}