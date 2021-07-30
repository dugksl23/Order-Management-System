package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class OrderRequestDto {

    @NotNull
    //@JsonIgnore
    private int orderNumber = 0;
    @NotNull
    private String card;
    @NotNull
    private boolean usageStatus = UsageStatusConfiguration.USAGE_STATUS;

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .orderNumber(orderNumber)
                .card(card)
                .usageStatus(usageStatus)
                .build();
    }

}
