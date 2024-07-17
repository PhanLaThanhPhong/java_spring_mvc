package com.example.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.laptopshop.service.UserService;
import com.example.laptopshop.domain.User;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("test", "test");
        return "home";
    }

    @RequestMapping("/admin/user")
    public String getCreateUserPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        System.out.println(user);
        this.userService.handleSaveUser(user);
        return "home";
    }
}