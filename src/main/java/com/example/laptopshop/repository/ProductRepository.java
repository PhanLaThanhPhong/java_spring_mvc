package com.example.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(long id);
    void deleteById(long id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAll(Specification<Product> specification,Pageable pageable);
}
