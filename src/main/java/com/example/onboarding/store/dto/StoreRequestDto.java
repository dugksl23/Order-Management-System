package com.example.onboarding.store.dto;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.entity.StoreEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreRequestDto {

    @NotNull
    private String name;
    @NotNull
    private int contactNumber;
    @NotNull
    private String address;
    @NotNull
    private boolean usageStatus = UsageStatusConfiguration.USAGE_STATUS;

    public StoreEntity toStoreEntity() {
        return StoreEntity.builder()
                .name(name)
                .contactNumber(contactNumber)
                .address(address)
                .usageStatus(usageStatus)
                .build();
    }

}
