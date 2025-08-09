package com.fulfilment.fulfillment_request_adapter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulfilment.fulfillment_request_adapter.DTO.FulfillmentRequestAdapter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FulfillmentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String topic = "order-fulfilment-topic";

    public FulfillmentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendFulfillmentRequest(FulfillmentRequestAdapter request) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(request);
        kafkaTemplate.send(topic, message);
    }

}
