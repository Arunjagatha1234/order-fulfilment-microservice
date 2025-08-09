package com.fulfilment.fulfillment_status_adapter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_fulfillment_status")
@Data
public class FulfillmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId;


    private String productName;

    private String region;

    private int quantity;

    private String status; // e.g., CONFIRMED, FAILED

}
