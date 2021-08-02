package com.example.onboarding.order.entity;


import com.example.onboarding.store.entity.StoreEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    private boolean usageStatus;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "storeNumber")
    @Setter
    private StoreEntity store;

}
