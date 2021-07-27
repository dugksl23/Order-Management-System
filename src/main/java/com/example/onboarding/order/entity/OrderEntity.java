package com.example.onboarding.order.entity;


import com.example.onboarding.store.entity.StoreEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderNumber;
    private String orderer;
    @CreationTimestamp
    private LocalDateTime orderDateCreated;
    @UpdateTimestamp
    private LocalDateTime orderDateUpdated;
    private String storeName;
    private String storeAddress;
    private boolean usageStatus;

    // 자식 Entity에서는 ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "storeNo")
    private StoreEntity stores;

}
