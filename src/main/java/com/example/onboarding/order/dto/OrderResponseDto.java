package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.entity.StoreEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseDto {

    private int orderNumber;
    private String card;
    private LocalDateTime orderDateCreated;
    private LocalDateTime orderDateUpdated;
    private boolean usageStatus;
    private InnerStoreResponseDto store;

    public OrderResponseDto(OrderEntity orderEntity) {

        this.orderNumber = orderEntity.getOrderNumber();
        this.card = orderEntity.getCard();
        this.orderDateCreated = orderEntity.getOrderDateCreated();
        this.orderDateUpdated = orderEntity.getOrderDateUpdated();
        this.usageStatus = orderEntity.isUsageStatus();
        this.store = new InnerStoreResponseDto(orderEntity.getStore());

    }

    @Getter
    private class InnerStoreResponseDto {

        private int storeNumber;
        private String name;
        private LocalDateTime entryDate;
        private int contactNumber;
        private String address;
        private boolean usageStatus;



        public InnerStoreResponseDto(StoreEntity storeEntity) {

            this.storeNumber = storeEntity.getStoreNumber();
            this.name = storeEntity.getName();
            this.entryDate = storeEntity.getEntryDate();
            this.contactNumber = storeEntity.getStoreNumber();
            this.address = storeEntity.getAddress();
            this.usageStatus = storeEntity.isUsageStatus();

        }

    }

}
