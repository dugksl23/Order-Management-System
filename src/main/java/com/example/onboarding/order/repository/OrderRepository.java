package com.example.onboarding.order.repository;

import com.example.onboarding.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Modifying
    @Query(value = "UPDATE OrderEntity o set o.usageStatus = false where o.orderNumber = :orderNumber")
    void deleteOrderStatus(int orderNumber);

    Optional<OrderEntity> findByOrderNumberAndUsageStatus(int orderNumber, boolean usageStatus);

    List<OrderEntity> findAllByUsageStatus(boolean usageStatus);


}
