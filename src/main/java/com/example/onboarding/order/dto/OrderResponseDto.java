package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class OrderResponseDto {

    private int orderNumber;
    private String orderer;
    private LocalDateTime orderDateCreated;
    private LocalDateTime orderDateUpdated;
    private String storeName;
    private String storeAddress;
    private boolean usageStatus;

    public OrderResponseDto(OrderEntity orderEntity) {
        this.orderNumber = orderEntity.getOrderNumber();
        this.orderer = orderEntity.getOrderer();
        this.orderDateCreated = orderEntity.getOrderDateCreated();
        this.orderDateUpdated = orderEntity.getOrderDateUpdated();
        this.storeName = orderEntity.getStoreName();
        this.storeAddress = orderEntity.getStoreAddress();
        this.usageStatus = orderEntity.isUsageStatus();

    }
}
