package com.example.onboarding.store.entity;


import com.example.onboarding.order.entity.OrderEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder //builder class 내에서는 모든 파라미터를 갖는 생성자를 필요로 한다. 일부 멤버변수만 갖는 생성자 함수만 존재할 경우에도 같은 에러가 나타난다. 기본생성자도 포함.
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class StoreEntity {

    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int storeNumber;

    private String name;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime entryDate;
    private int contactNumber;
    private String address;
    private boolean usageStatus;

    @OneToMany(mappedBy = "stores") // many이기에 list
    private List<OrderEntity> orders;

    public void update(String name, int contactNumber, String address) {

        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;

    }


}
