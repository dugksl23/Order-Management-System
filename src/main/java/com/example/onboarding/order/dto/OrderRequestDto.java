package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {


    private int oderNumber;
    private String orderer;
    private String storeName;
    private String storeAddress;
    private boolean usageStatus = false;

    public OrderEntity toEntity() {

        return OrderEntity.builder()
                .orderer(orderer)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .usageStatus(usageStatus)
                .build();
    }

}
