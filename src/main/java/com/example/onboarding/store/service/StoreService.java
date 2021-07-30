package com.example.onboarding.store.service;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.entity.StoreEntity;
import com.example.onboarding.store.repository.StoreRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
/** @RequiredConstructor 를 통한 final 변수의 생성자 주입방식을 쓰는 이유
1. 단일 책임 원칙
 하나의 객체는 하나의 기능만을 관리 또는 수행되어져야 한다. ex) StoreRepository storeRepository
 즉, 하나의 객체만 갖는다는 것은 하나의 객체로 하나의 기능만을 독립적으로 수행함을 의미한다.
 객체가 많다는 것은 많은 클래스가 많은 책임을 갖는다는 것을 의미한다.

 2. fianl 키워드 사용
 @Autowired와 같이 field injection을 사용할 때에는 final 키워드를 사용할 수 없지만,
 consturctor injection을 사용할 때에는 final 키워드를 사용 가능.
 그렇기에 immutable 하게 사용 가능.

 3. 순환참조 방지

 객체의 의존성을 추가하다보면 발생하는 문제가 순환참조이다. ex) entity의 양방향 참조
 생정자 주입방식을 통해서, 아래와 같이 어플리케이션 실행 시점(컴파일 시점)에서 순환 참조를 확인가능하다.
 filed inject과 setter Injection은 메서드 실행 시점에서만 발견 가능.

 4. DI 컨테이너와 겷바도가 낮기 때문에 테스트하기 유리.

 스프링 컨테이너의 도움 없이 우리는 간단히 테스트 가능하다.
 예를 들면 테스트 코드 파일에서 테스트 할 레이어의 autowired를 하지 않아도
 생성자 주입방식으로 바로 테스트가 가능하기에 테스트 시점에서 bean 등록을 할 수 있다는 점이다..

*/
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    // --> select 문에서는 readonly True를 하면 변경감지와 같은 무거운 작업들을 안해도 된다.
    // 반대로 update에서는 readonly는 안된다.
    public StoreResponseDto fetchStore(int storeNumber) {

        StoreEntity storeEntity = storeRepository.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS).orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));
        StoreResponseDto storeResponseDto = new StoreResponseDto(storeEntity);

        return storeResponseDto;

    }

    public List<StoreResponseDto> fetchAll() {

        List<StoreResponseDto> storeList = storeRepository.findAllByUsageStatus(UsageStatusConfiguration.USAGE_STATUS)
                .stream()
                .map(StoreResponseDto::new)
                .collect(Collectors.toList());

        return storeList;

    }


    @Transactional
    public int registerStore(StoreRequestDto StoreRequestDto) {

        StoreEntity storeEntity = StoreRequestDto.toStoreEntity();
        return storeRepository.save(storeEntity).getStoreNumber();

    }

    @Transactional
    public StoreResponseDto updateStore(int storeNumber, StoreRequestDto storeRequestDto) {

        StoreEntity storeEntity = storeRepository.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS)
                .orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));

        storeEntity.update(storeRequestDto.getName(), storeRequestDto.getContactNumber(), storeRequestDto.getAddress());
        // *enum class를 통해서 상수값 관리.

        return new StoreResponseDto(storeEntity);

    }

    @Transactional
    public ResponseEntity<?> deleteStore(int storeNumber) {

        StoreEntity storeEntity = storeRepository.findById(storeNumber).orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));
        //storeRepository.updateUsageStatus(storeNumber);
        storeRepository.delete(storeEntity);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
