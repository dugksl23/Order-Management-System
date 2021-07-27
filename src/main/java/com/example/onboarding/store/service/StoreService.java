package com.example.onboarding.store.service;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.entity.StoreEntity;
import com.example.onboarding.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreResponseDto findStore(int storeNumber) {

        StoreEntity storeEntity = storeRepository.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS).orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));
        return new StoreResponseDto(storeEntity);

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
    public ResponseEntity<?> updateStore(StoreRequestDto requestDto) {

        StoreEntity storeEntity = requestDto.toStoreEntity();
        storeRepository.save(storeEntity);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    public ResponseEntity<?> deleteStore(int storeNumber) {

        StoreEntity storeEntity = storeRepository.findById(storeNumber).orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));
        storeRepository.updateUsageStatus(storeEntity.getStoreNumber());
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
