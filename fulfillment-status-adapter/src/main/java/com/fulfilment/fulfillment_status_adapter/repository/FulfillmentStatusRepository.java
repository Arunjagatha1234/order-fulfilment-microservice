package com.fulfilment.fulfillment_status_adapter.repository;

import com.fulfilment.fulfillment_status_adapter.entity.FulfillmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FulfillmentStatusRepository extends JpaRepository<FulfillmentStatus,Long> {
}
