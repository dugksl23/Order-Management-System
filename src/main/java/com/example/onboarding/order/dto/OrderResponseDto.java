package com.example.onboarding.order.dto;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.entity.StoreEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
// Data Binding을 할 때 getter와 setter로 한다.
public class OrderResponseDto {

    private int orderNumber;
    private String card;
    private LocalDateTime orderDateCreated;
    private LocalDateTime orderDateUpdated;
    private boolean usageStatus;
    private InnerStoreResponseDto store;
    //ManyToOne 이기에 주문별 가게는 하나일 수밖에 없다.
    //dto끼리도 무한참조가능하기에 InnerClass로 내부에 StoreResponseDto를 만들어줌.

    public OrderResponseDto(OrderEntity orderEntity) {

        this.orderNumber = orderEntity.getOrderNumber();
        this.card = orderEntity.getCard();
        this.orderDateCreated = orderEntity.getOrderDateCreated();
        this.orderDateUpdated = orderEntity.getOrderDateUpdated();
        this.usageStatus = orderEntity.isUsageStatus();
        this.store = new InnerStoreResponseDto(orderEntity.getStores());

    }

    // 양방향 참조로 인해 서로 긴밀한 관계에 있기 때문에 내부 클래스로 선언.
    // inner class는 getter의 역할만 하면 되기에 setter는 필요없다.
    @Getter
    private class InnerStoreResponseDto {

        private int storeNumber;
        private String name;
        private LocalDateTime entryDate;
        private int contactNumber;
        private String address;
        private boolean usageStatus;

        public InnerStoreResponseDto(StoreEntity storeEntity) {

            this.storeNumber = storeEntity.getStoreNumber();
            this.name = storeEntity.getName();
            this.entryDate = storeEntity.getEntryDate();
            this.contactNumber = storeEntity.getStoreNumber();
            this.address = storeEntity.getAddress();
            this.usageStatus = storeEntity.isUsageStatus();

        }

    }


}
