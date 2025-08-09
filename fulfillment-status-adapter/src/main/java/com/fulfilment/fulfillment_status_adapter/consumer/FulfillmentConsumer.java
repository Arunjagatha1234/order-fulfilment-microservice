package com.fulfilment.fulfillment_status_adapter.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulfilment.fulfillment_status_adapter.entity.FulfillmentStatusDTO;
import com.fulfilment.fulfillment_status_adapter.repository.FulfillmentStatusRepository;
import com.fulfilment.fulfillment_status_adapter.entity.FulfillmentStatus;
import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FulfillmentConsumer {
    private final FulfillmentStatusRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FulfillmentConsumer(FulfillmentStatusRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "order-fulfilment-topic", groupId = "status-consumer-group")
    public void consume(String message) {
        try {
            FulfillmentStatusDTO dto = objectMapper.readValue(message, FulfillmentStatusDTO.class);

            FulfillmentStatus status = new FulfillmentStatus();
            status.setOrderId(dto.getOrderId());
            status.setProductName(dto.getProductName());
            status.setRegion(dto.getCustomerRegion());
            status.setQuantity(dto.getQuantity());
            status.setStatus("confirmed");

            repository.save(status);

            System.out.println("Order processed and saved: " + status);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
