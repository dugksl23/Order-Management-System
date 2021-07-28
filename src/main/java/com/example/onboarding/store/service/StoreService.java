package com.example.onboarding.store.service;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.entity.StoreEntity;
import com.example.onboarding.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
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
    public ResponseEntity<?> updateStore(int storeNumber, StoreRequestDto storeRequestDto) {

        StoreEntity storeEntity = storeRepository.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS).orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));
        storeEntity.update(storeRequestDto.getName(), storeRequestDto.getContactNumber(), storeRequestDto.getAddress());

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<?> deleteStore(int storeNumber) {

        StoreEntity storeEntity = storeRepository.findById(storeNumber).orElseThrow(() -> new NullPointerException("조회 정보가 없습니다."));
        storeEntity.update(storeEntity.getName(), storeEntity.getContactNumber(), storeEntity.getAddress());
        //storeRepository.updateUsageStatus(storeEntity.getStoreNumber());
        // transactional annotation 덕분에 영속성이 전이가 되고 있기에 save 함수는 불필요하다.

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public StoreEntity findByStoreNumber(int storeNumber) {

        return storeRepository.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS).get();
    }


}
