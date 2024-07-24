package com.example.laptopshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.laptopshop.service.UserService;
import com.example.laptopshop.domain.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // url mapping to home page
    @RequestMapping("/")
    public String getHomePage(Model model){
        List<User> listUser = this.userService.getAllUsers();
        System.out.println(listUser);
        model.addAttribute("test", "test");
        return "home";
    }

    // url mapping to table user page
    @RequestMapping("/admin/user")
    public String getTableUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    //url mapping to user detail page
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id){
        Optional<User> user = this.userService.getUserById(id);
        model.addAttribute("user", user.get());
        return "admin/user/show";
    }
    
    // url mapping to create user page
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    
    // url mapping to action create user
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user) {
        System.out.println(user);
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    //url mapping to update user page
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id){
        User currentUser = this.userService.getUserById(id).get();
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    // url mapping to action update user
    @PostMapping("/admin/user/update")
    public String updateUserPage(Model model, @ModelAttribute("newUser") User user){
        User currentUser = this.userService.getUserById(user.getId()).get();
        if (currentUser != null){
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id){
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postMethodName(Model model, @ModelAttribute("newUser") User user) {
        this.userService.deleteAUser(user.getId());
        return "redirect:/admin/user";
    }
    
}
