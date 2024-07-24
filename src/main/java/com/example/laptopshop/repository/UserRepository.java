package com.example.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.domain.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void deleteById(long id);
    List<User> findAll();
    List<User> findByEmail(String email);
    Optional<User> findById(long id);
}
