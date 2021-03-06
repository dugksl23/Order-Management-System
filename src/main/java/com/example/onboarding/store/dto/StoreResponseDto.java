package com.example.onboarding.store.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.entity.StoreEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StoreResponseDto {

    private int StoreNumber;
    private String name;
    private LocalDateTime entryDate;
    private int contactNumber;
    private String address;
    private boolean usageStatus;
    private Collection<InnerOrderResponseDto> orders;

    public StoreResponseDto(StoreEntity storeEntity) {

        this.StoreNumber = storeEntity.getStoreNumber();
        this.name = storeEntity.getName();
        this.entryDate = storeEntity.getEntryDate();
        this.contactNumber = storeEntity.getContactNumber();
        this.address = storeEntity.getAddress();
        this.usageStatus = storeEntity.isUsageStatus();
        this.orders = toOrderResponseDtoList(storeEntity.getOrders());

    }

    private List<InnerOrderResponseDto> toOrderResponseDtoList(List<OrderEntity> orders) {

        List<InnerOrderResponseDto> orderResponseDtoList = orders.stream()
                .map(orderEntity -> new InnerOrderResponseDto(orderEntity))
                .collect(Collectors.toList());

        return orderResponseDtoList;

    }

    @Getter
    private class InnerOrderResponseDto {

        private int orderNumber;
        private String card;
        private LocalDateTime orderDateCreated;
        private boolean usageStatus;

        public InnerOrderResponseDto(OrderEntity orderEntity) {

            this.orderNumber = orderEntity.getOrderNumber();
            this.card = orderEntity.getCard();
            this.orderDateCreated = orderEntity.getOrderDateCreated();
            this.usageStatus = orderEntity.isUsageStatus();

        }
    }

}
