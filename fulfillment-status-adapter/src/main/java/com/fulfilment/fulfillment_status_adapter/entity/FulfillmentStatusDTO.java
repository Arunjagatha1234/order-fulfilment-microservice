package com.fulfilment.fulfillment_status_adapter.entity;

import lombok.Data;

@Data
public class FulfillmentStatusDTO {
    private int orderId;
    private String productName;
    private String customerRegion;
    private int quantity;
}
