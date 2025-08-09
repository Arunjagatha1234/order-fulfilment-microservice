package com.fulfilment.fulfillment_status_adapter.controller;


import com.fulfilment.fulfillment_status_adapter.entity.FulfillmentStatus;
import com.fulfilment.fulfillment_status_adapter.repository.FulfillmentStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = "order-fulfilment-topic")
public class FulfillmentKafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private FulfillmentStatusRepository repository;

    @Test
    public void shouldSaveToDBWhenKafkaMessageReceived() throws Exception {
        String message = """
        {
            "orderId": 999,
            "productName": "TestProduct",
            "customerRegion": "East",
            "quantity": 5
        }
        """;

        kafkaTemplate.send("order-fulfilment-topic", message);

        // Wait for consumer to process
        Thread.sleep(1000);

        Optional<FulfillmentStatus> saved = repository.findAll().stream()
                .filter(o -> o.getOrderId() == 999).findFirst();

        assertTrue(saved.isPresent());
        assertEquals("TestProduct", saved.get().getProductName());
    }
}
