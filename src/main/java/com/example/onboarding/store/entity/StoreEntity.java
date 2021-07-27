package com.example.onboarding.store.entity;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.dto.StoreResponseDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int storeNumber;
    private String name;
    @CreationTimestamp
    private LocalDateTime entryDate;
    private int contactNumber;
    private String address;
    private boolean usageStatus;

    //부모 entity 에서는 OneToMany, 관계의 주인은 자식
    @OneToMany(mappedBy = "stores")
    private Collection<OrderEntity> orders;

}
