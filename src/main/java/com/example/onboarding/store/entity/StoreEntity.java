package com.example.onboarding.store.entity;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class StoreEntity {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeNumber;
    private String name;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime entryDate;
    private int contactNumber;
    private String address;
    private boolean usageStatus;

    @OneToMany(mappedBy = "stores") //
    private List<OrderEntity> orders;

    public void update(String name, int contactNumber, String address) {

        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;

    }


}
