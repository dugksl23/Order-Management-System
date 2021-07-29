package com.example.onboarding.order.entity;


import com.example.onboarding.store.entity.StoreEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderNumber;
    private String card;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime orderDateCreated;
    @UpdateTimestamp
    private LocalDateTime orderDateUpdated;
    private boolean usageStatus;

    // 자식 Entity에서는 ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "storeNumber")
    private StoreEntity stores;

    public void setStore(StoreEntity storeEntity){

        this.stores = storeEntity;

    }

}
