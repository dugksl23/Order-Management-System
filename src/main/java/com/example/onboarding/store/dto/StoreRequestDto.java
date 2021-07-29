package com.example.onboarding.store.dto;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.entity.StoreEntity;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
//기본생성자를 생성하고, 외부에서 접근을 제어하고, 커스텀 contructor로 모든 인자를 다 채우게하여 초기화하기.
public class StoreRequestDto {

    private int storeNumber;
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
                .storeNumber(storeNumber)
                .name(name)
                .contactNumber(contactNumber)
                .address(address)
                .usageStatus(usageStatus)
                .build();
    }

}
