package com.example.onboarding.store.dto;


import com.example.onboarding.store.entity.StoreEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class StoreResponseDto {

    private int StoreNumber;
    private String name;
    private LocalDateTime entryDate;
    private int contactNumber;
    private String address;
    private boolean usageStatus;

    public StoreResponseDto(StoreEntity storeEntity) {
        StoreNumber = storeEntity.getStoreNumber();
        this.name = storeEntity.getName();
        this.entryDate = storeEntity.getEntryDate();
        this.contactNumber = storeEntity.getContactNumber();
        this.address = storeEntity.getAddress();
        this.usageStatus = storeEntity.isUsageStatus();
    }
}
