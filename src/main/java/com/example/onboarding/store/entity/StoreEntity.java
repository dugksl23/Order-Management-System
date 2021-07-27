package com.example.onboarding.store.entity;


import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.store.dto.StoreResponseDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder //builder class 내에서는 모든 파라미터를 갖는 생성자를 필요로 한다. 일부 멤버변수만 갖는 생성자 함수만 존재할 경우에도 같은 에러가 나타난다. 기본생성자도 포함.
@AllArgsConstructor
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

    public StoreEntity update(StoreResponseDto StoreResponseDto) {

        return StoreEntity.builder()
                .storeNumber(StoreResponseDto.getStoreNumber())
                .name(StoreResponseDto.getName())
                .entryDate(StoreResponseDto.getEntryDate())
                .contactNumber(StoreResponseDto.getContactNumber())
                .address(StoreResponseDto.getAddress())
                .usageStatus(StoreResponseDto.isUsageStatus())
                .build();

    }


}
