package com.order.order_service.entity;
import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "customer name is required")
    private String customerName;

    @NotBlank(message="product name is required")
    private String productName;

    @NotNull
    @Min(value = 1,message = "quantity must be greater than or equals to 1")
    private int quantity;

    private String status;

    private LocalDateTime created_At =LocalDateTime.now();

}
