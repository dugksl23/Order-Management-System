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

    //EAGER와 Lazy
    // CascdeType.All 부모 엔티티 삭제시 자식 엔티티도 삭제 관계에 대해서 알아보기.
    @ManyToOne(fetch = FetchType.EAGER)
    // remove - 연관관계있는 상태에서 부모 엔티티를 지우려면, 자식 엔티티부터 지워야 한다.
    @JoinColumn(name = "storeNumber")
    @Setter
    private StoreEntity store;

}
