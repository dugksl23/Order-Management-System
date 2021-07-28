package com.example.onboarding.store.dto;


import com.example.onboarding.order.dto.OrderResponseDto;
import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.entity.StoreEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
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
        this.orders = this.toReResponseDtoList(storeEntity);

    }

    private List<OrderResponseDto> toReResponseDtoList(StoreEntity storeEntity) {

        List<OrderResponseDto> orderResponseDtoList = storeEntity.getOrders().stream()
                .map(orderEntity -> new OrderResponseDto(orderEntity))
                .collect(Collectors.toList());

        return orderResponseDtoList;

    }

}
