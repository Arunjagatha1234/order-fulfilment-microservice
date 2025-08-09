package com.order.order_service.controller;

import com.order.order_service.Service.OrderService;
import com.order.order_service.entity.Order;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/orders")
public class OrderController {
    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order){
        return ResponseEntity.ok(orderService.createOrder(order));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id){
        Optional<Order> order = orderService.getOrder(id);
        if(order.isPresent()){
            return ResponseEntity.ok(order.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
