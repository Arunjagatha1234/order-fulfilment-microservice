package com.order.order_service.service;

import com.order.order_service.Service.OrderService;
import com.order.order_service.entity.FulfillmentRequest;
import com.order.order_service.entity.Order;
import com.order.order_service.respository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrder_shouldSaveOrderAndCallRest(){

        Order order = new Order();
        order.setCustomerName("Arun");
        order.setProductName("shoes");
        order.setQuantity(2);

        Order saved = new Order();
        saved.setId(1L);
        saved.setCustomerName("Arun");
        saved.setProductName("Shoes");
        saved.setQuantity(2);

        when(orderRepository.save(any(Order.class))).thenReturn(saved);

        Order result = orderService.createOrder(order);

        assertEquals("PENDING", result.getStatus());
        verify(restTemplate).postForObject("http://localhost:8082/fulfill", any(FulfillmentRequest.class), eq(String.class));
        verify(orderRepository).save(any(Order.class));


    }

}
