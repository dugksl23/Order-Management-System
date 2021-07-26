package com.example.onboarding.store.entity;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int storeNo;
    private String name;
    @CreationTimestamp
    private LocalDateTime entryDate;
    private int contactNumber;
    private String address;
    //부모 entity 에서는 OneToMany, 관계의 주인은 자식
    @OneToMany(mappedBy = "stores")
    private Collection<OrderEntity> orders;

}
