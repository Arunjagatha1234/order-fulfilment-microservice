package com.fulfilment.fulfillment_request_adapter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, topics = "order-fulfilment-topic")
public class FulfillmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSendKafkaMessageWhenPOST() throws Exception {
        String json = """
        {
            "orderId": 1,
            "productName": "Tablet",
            "customerRegion": "USA",
            "quantity": 1
        }
        """;

        mockMvc.perform(post("/fulfill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }


}
