package com.fulfilment.fulfillment_request_adapter.DTO;
import lombok.Data;

@Data
public class FulfillmentRequestAdapter {

    private Long orderId;
    private String productName;
    private String customerRegion;
    private int quantity;
}
