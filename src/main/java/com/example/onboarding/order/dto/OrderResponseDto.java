package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDto {

    private int orderNumber;
    private String card;
    private LocalDateTime orderDateCreated;
    private LocalDateTime orderDateUpdated;
    private boolean usageStatus;

    public OrderResponseDto(OrderEntity orderEntity) {

        this.orderNumber = orderEntity.getOrderNumber();
        this.card = orderEntity.getCard();
        this.orderDateCreated = orderEntity.getOrderDateCreated();
        this.orderDateUpdated = orderEntity.getOrderDateUpdated();
        this.usageStatus = orderEntity.isUsageStatus();

    }
}
