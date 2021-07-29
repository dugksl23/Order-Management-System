package com.example.onboarding.store.dto;


import com.example.onboarding.order.dto.OrderResponseDto;
import com.example.onboarding.store.entity.StoreEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Collection<OrderResponseDto> orders;

    public StoreResponseDto(StoreEntity storeEntity) {

        this.StoreNumber = storeEntity.getStoreNumber();
        this.name = storeEntity.getName();
        this.entryDate = storeEntity.getEntryDate();
        this.contactNumber = storeEntity.getContactNumber();
        this.address = storeEntity.getAddress();
        this.usageStatus = storeEntity.isUsageStatus();
        this.orders = this.toOrderResponseDtoList(storeEntity);

    }

    private List<OrderResponseDto> toOrderResponseDtoList(StoreEntity storeEntity) {

        List<OrderResponseDto> orderResponseDtoList = storeEntity.getOrders().stream()
                .map(orderEntity -> new OrderResponseDto(orderEntity))
                .collect(Collectors.toList());

        return orderResponseDtoList;

    }

}
