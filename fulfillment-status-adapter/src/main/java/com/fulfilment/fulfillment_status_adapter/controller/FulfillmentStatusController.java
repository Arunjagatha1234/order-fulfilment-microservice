package com.fulfilment.fulfillment_status_adapter.controller;

import com.fulfilment.fulfillment_status_adapter.repository.FulfillmentStatusRepository;
import com.fulfilment.fulfillment_status_adapter.entity.FulfillmentStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/status")
public class FulfillmentStatusController {

    private final FulfillmentStatusRepository repository;

    public FulfillmentStatusController(FulfillmentStatusRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<FulfillmentStatus> getStatus(@PathVariable Long orderId) {
        Optional<FulfillmentStatus> status = repository.findById(orderId);
        return status.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
