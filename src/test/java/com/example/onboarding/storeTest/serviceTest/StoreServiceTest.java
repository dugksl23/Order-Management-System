package com.example.onboarding.storeTest.serviceTest;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.entity.StoreEntity;
import com.example.onboarding.store.repository.StoreRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Service
public class StoreServiceTest {

    @Autowired
    private StoreRepository storeRepositoryTest;

    @Test
    @DisplayName("식당 정보 수정 Test")
    public void updateStoreTest() throws Exception {

        // given...
        int storeNumber = 3;
        String address = "강남구";

        // when...
        StoreEntity storeEntity = storeRepositoryTest.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS)
                .orElseThrow(() -> new NullPointerException("조회 결과 없음"));

        StoreResponseDto storeDto = new StoreResponseDto(storeEntity);
        storeDto.setAddress(address);

//        StoreEntity entity = StoreEntity.builder()
//                .storeNumber(storeDto.getStoreNumber())
//                .name(storeDto.getName())
//                .entryDate(storeDto.getEntryDate())
//                .contactNumber(storeDto.getContactNumber())
//                .address(storeDto.getAddress())
//                .usageStatus(storeDto.isUsageStatus())
//                .build();

        StoreEntity entity = storeEntity.update(storeDto);
        StoreEntity responseEntity = storeRepositoryTest.save(entity);

        // then...
        Assert.assertEquals("주소 : ", address, responseEntity.getAddress());


    }

}
