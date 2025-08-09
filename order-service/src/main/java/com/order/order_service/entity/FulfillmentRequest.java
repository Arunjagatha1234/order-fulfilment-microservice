package com.order.order_service.entity;

import lombok.Data;

@Data
public class FulfillmentRequest {

    private Long orderId;
    private String productName;
    private String customerRegion;
    private int quantity;
}
