package com.example.onboarding.store.dto;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.entity.StoreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter //StoreEntity Update 및 매개변수단에서의 object 매핑을 위한 getter
        //@Getter가 없을 때에는  No serializer found for class Exception 발생
public class StoreRequestDto {

    @JsonIgnore // 공통적으로 쓸 때에는 아래의 변수가 안 쓰인다면 JsonIgnore 로....
                // 즉 해당 변수를 toString 또는 serializer 하지 않겠다는 의미.
    //@NotNull
    private Integer storeNumber = 0; //Int는 nullsafe이 안됨.
    @Getter // 필요한 변수에서만 getter setter 어노테이션 사용
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
