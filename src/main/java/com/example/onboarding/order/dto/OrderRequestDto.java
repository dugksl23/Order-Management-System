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
    private String card;
    @NotNull
    private boolean usageStatus = UsageStatusConfiguration.USAGE_STATUS;

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .card(card)
                .usageStatus(usageStatus)
                .build();
    }

}
