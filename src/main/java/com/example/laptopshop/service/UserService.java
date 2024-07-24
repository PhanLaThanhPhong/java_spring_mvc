package com.example.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.laptopshop.domain.User;
import com.example.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    // Contructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public List<User> getUsersByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(long id){
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user){
        User currentUser = this.userRepository.save(user);
        System.out.println(currentUser);
        return this.userRepository.save(user);
    }

    public void deleteAUser(long id){
        this.userRepository.deleteById(id);
    }
}