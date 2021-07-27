package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.order.statics.UsageStatusConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRequestDto {


    @NotNull
    private String orderer;
    @NotNull
    private String storeName;
    @NotNull
    private String storeAddress;
    @NotNull
    private boolean usageStatus = UsageStatusConfiguration.USAGE_STATUS;

    public OrderRequestDto(String orderer, String storeName, String storeAddress, boolean usageStatus) {
        this.orderer = orderer;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.usageStatus = usageStatus;
    }

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .orderer(orderer)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .usageStatus(usageStatus)
                .build();
    }

}
