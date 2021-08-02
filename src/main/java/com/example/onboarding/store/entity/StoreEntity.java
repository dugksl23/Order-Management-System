package com.example.onboarding.store.entity;


import com.example.onboarding.order.entity.OrderEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@NamedEntityGraph(name = "StoreEntity.orders", attributeNodes = @NamedAttributeNode("orders"))
public class StoreEntity {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeNumber;
    private String name;
    @Column(updatable = false)
    @CreationTimestamp // hibernate
    @CreatedDate // spring jpa
    private LocalDateTime entryDate;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    private int contactNumber;
    private String address;
    private boolean usageStatus;

    //@BatchSize(size = 2) // batch(묶음 처리) size 지정하여 불러온다.
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    // 즉시로딩 (EAGER) 전략으로 변경
    private List<OrderEntity> orders;

    public void update(String name, int contactNumber, String address) {

        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;

    }

}
