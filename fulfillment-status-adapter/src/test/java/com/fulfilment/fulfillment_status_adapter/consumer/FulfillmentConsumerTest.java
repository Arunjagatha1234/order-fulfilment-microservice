package com.fulfilment.fulfillment_status_adapter.consumer;

import com.fulfilment.fulfillment_status_adapter.entity.FulfillmentStatus;
import com.fulfilment.fulfillment_status_adapter.repository.FulfillmentStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class FulfillmentConsumerTest {

    @MockBean
    private FulfillmentStatusRepository repository;

    @Autowired
    private FulfillmentConsumer consumer;

    @Test
    public void shouldConsumeMessageAndSaveToDB() throws Exception {
        String message = """
        {
            "orderId": 1,
            "productName": "Bag",
            "customerRegion": "USA",
            "quantity": 1
        }
        """;

        consumer.consume(message);

        verify(repository, times(1)).save(any(FulfillmentStatus.class));
    }
}



