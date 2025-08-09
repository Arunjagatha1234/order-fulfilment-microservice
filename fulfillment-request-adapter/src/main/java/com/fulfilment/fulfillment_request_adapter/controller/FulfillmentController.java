package com.fulfilment.fulfillment_request_adapter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fulfilment.fulfillment_request_adapter.DTO.FulfillmentRequestAdapter;
import com.fulfilment.fulfillment_request_adapter.service.FulfillmentProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fulfill")
public class FulfillmentController {
    private final FulfillmentProducer producer;

    public FulfillmentController(FulfillmentProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> fulfill(@RequestBody FulfillmentRequestAdapter request) throws JsonProcessingException {
        producer.sendFulfillmentRequest(request);
        return ResponseEntity.ok("Fulfillment request sent.");
    }

}
