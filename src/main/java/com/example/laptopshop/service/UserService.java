package com.example.laptopshop.service;

import org.springframework.stereotype.Service;

import com.example.laptopshop.domain.User;
import com.example.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user){
        User currentUser = this.userRepository.save(user);
        System.out.println(currentUser);
        return this.userRepository.save(user);
    }
}