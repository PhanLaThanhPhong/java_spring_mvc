package com.example.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.domain.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Order findById(long id);

    Page<Order> findAll(Pageable pageable);
}
