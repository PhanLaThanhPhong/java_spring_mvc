package com.example.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(long id);
    void deleteById(long id);
}