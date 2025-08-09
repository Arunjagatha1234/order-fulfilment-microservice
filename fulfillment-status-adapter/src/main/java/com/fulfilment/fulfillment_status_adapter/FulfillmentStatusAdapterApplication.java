package com.fulfilment.fulfillment_status_adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class FulfillmentStatusAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentStatusAdapterApplication.class, args);
	}

}
