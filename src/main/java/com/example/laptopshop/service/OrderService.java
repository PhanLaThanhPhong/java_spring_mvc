package com.example.laptopshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.laptopshop.domain.Order;
import com.example.laptopshop.domain.OrderDetail;
import com.example.laptopshop.repository.OrderDetailRepository;
import com.example.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Page<Order> getAllOrders(Pageable pageable){
        return this.orderRepository.findAll(pageable);
    }

    public List<OrderDetail> getOrderDetailsByOrder(Order order){
        return this.orderDetailRepository.findByOrder(order);
    }

    public Order getOrderById(long id){
        return this.orderRepository.findById(id);
    }

    public Order handleSaveOrder(Order order){
        return this.orderRepository.save(order);
    }

    public void handleDeleteOrder(Order order){
        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrder(order);
        for (OrderDetail cd : orderDetails){
            this.orderDetailRepository.delete(cd);
        }
        this.orderRepository.delete(order);
    }

    public long countOrders(){
        return this.orderRepository.count();
    }
}
