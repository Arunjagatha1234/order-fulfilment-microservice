package com.order.order_service.Service;

import com.order.order_service.entity.FulfillmentRequest;
import com.order.order_service.entity.Order;
import com.order.order_service.respository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order createOrder( Order order){
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        // Create FulfillmentRequest DTO
        FulfillmentRequest request = new FulfillmentRequest();
        request.setOrderId(savedOrder.getId());
        request.setProductName(savedOrder.getProductName());
        request.setCustomerRegion("USA-East"); // You can enhance logic here
        request.setQuantity(savedOrder.getQuantity());

        // Make REST call to Fulfillment Adapter
        restTemplate.postForObject("http://localhost:8082/fulfill", request, String.class);

        return savedOrder;

    }

    public Optional<Order> getOrder( Long id){
        return orderRepository.findById(id);

    }


}
