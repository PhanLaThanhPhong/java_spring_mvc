package com.example.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.laptopshop.domain.Order;
import com.example.laptopshop.domain.OrderDetail;
import com.example.laptopshop.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("admin/order")
    public String getOrderPage(Model model,
            @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()){
                page = Integer.parseInt(pageOptional.get());
            }else{}
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        Pageable pageable = PageRequest.of(page - 1, 4);
        Page<Order> orders = this.orderService.getAllOrders(pageable);
        model.addAttribute("orders", orders.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orders.getTotalPages());
        return "admin/order/show";
    }

    @GetMapping("admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        List<OrderDetail> orderDetails = this.orderService.getOrderDetailsByOrder(this.orderService.getOrderById(id));
        model.addAttribute("orderDetails", orderDetails);
        return "admin/order/detail";
    }
    
    @GetMapping("admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id) {
        Order currentOrder = this.orderService.getOrderById(id);
        model.addAttribute("newOrder", currentOrder);
        return "admin/order/update";
    }

    @PostMapping("admin/order/update")
    public String handleUpdateOrder(@ModelAttribute("newOrder") Order order) {
        Order currentOrder = this.orderService.getOrderById(order.getId());
        currentOrder.setStatus(order.getStatus());
        this.orderService.handleSaveOrder(currentOrder);
        return "redirect:/admin/order";
    }
    
    @GetMapping("admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        Order currentOrder = this.orderService.getOrderById(id);
        model.addAttribute("newOrder", currentOrder);
        return "admin/order/delete";
    }
    
    @PostMapping("admin/order/delete")
    public String handleDeleteOrder(@ModelAttribute("newOrder") Order order) {
        this.orderService.handleDeleteOrder(order);
        
        return "redirect:/admin/order";
    }
    
}
