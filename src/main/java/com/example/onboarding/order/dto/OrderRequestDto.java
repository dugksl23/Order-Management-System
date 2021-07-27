package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.common.statics.UsageStatusConfiguration;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrderRequestDto {


    @NotNull
    private String orderer;
    @NotNull
    private String storeName;
    @NotNull
    private String storeAddress;
    @NotNull
    private boolean usageStatus = UsageStatusConfiguration.USAGE_STATUS;

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .orderer(orderer)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .usageStatus(usageStatus)
                .build();
    }

}
