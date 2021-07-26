package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequestDto {

    @NotNull
    private int orderNumber;
    @NotNull
    private String orderer;
    @NotNull
    private String storeName;
    @NotNull
    private String storeAddress;
    @NotNull
    private boolean usageStatus = false;

    public OrderRequestDto(int orderNumber, String orderer, String storeName, String storeAddress, boolean usageStatus) {
        this.orderNumber = orderNumber;
        this.orderer = orderer;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.usageStatus = usageStatus;
    }

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .orderNumber(orderNumber)
                .orderer(orderer)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .usageStatus(usageStatus)
                .build();
    }

}
