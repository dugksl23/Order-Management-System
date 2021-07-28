package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.dto.StoreResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private int orderNumber;
    private String card;
    private LocalDateTime orderDateCreated;
    private LocalDateTime orderDateUpdated;
    private boolean usageStatus;
    private StoreResponseDto stores;
    //ManyToOne 이기에 주문별 가게는 하나일 수밖에 없다.
    //dto끼리도 무한참조가능

    public OrderResponseDto(OrderEntity orderEntity) {

        this.orderNumber = orderEntity.getOrderNumber();
        this.card = orderEntity.getCard();
        this.orderDateCreated = orderEntity.getOrderDateCreated();
        this.orderDateUpdated = orderEntity.getOrderDateUpdated();
        this.usageStatus = orderEntity.isUsageStatus();
        this.stores = toStoreResponseDto(orderEntity);

    }

    private StoreResponseDto toStoreResponseDto(OrderEntity orderEntity) {

        return new StoreResponseDto(orderEntity.getStores());

    }
}
