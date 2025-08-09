package com.fulfilment.fulfillment_request_adapter.service;

import com.fulfilment.fulfillment_request_adapter.DTO.FulfillmentRequestAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class FulfillmentProducerTest {

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private FulfillmentProducer producer;

    @Test
    public void shouldSendMessageToKafka() throws Exception {
        FulfillmentRequestAdapter req = new FulfillmentRequestAdapter();
        req.setOrderId(1L);
        req.setCustomerRegion("USA");
        req.setProductName("Bag");
        req.setQuantity(1);

        producer.sendFulfillmentRequest(req);

        verify(kafkaTemplate, times(1)).send(eq("order-fulfilment-topic"), any(String.class));
    }
}
