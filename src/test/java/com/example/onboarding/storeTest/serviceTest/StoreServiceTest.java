package com.example.onboarding.storeTest.serviceTest;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.store.entity.StoreEntity;
import com.example.onboarding.store.repository.StoreRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
@Service
public class StoreServiceTest {

    @Autowired
    private StoreRepository storeRepositoryTest;

    @Test
    @DisplayName("식당 정보 수정 Test")
    public void updateStoreTest() throws Exception {

        // given...
        int storeNumber = 5;
        String address = "강남구";

        // when...
        StoreEntity storeEntity = storeRepositoryTest.findByStoreNumberAndUsageStatus(storeNumber, UsageStatusConfiguration.USAGE_STATUS)
                .orElseThrow(() -> new NullPointerException("조회 결과 없음"));

        storeEntity.update(storeEntity.getName(), storeEntity.getContactNumber(), address);
        StoreEntity responseEntity = storeRepositoryTest.save(storeEntity);

        // then...
        Assert.assertEquals("주소 : ", address, responseEntity.getAddress());


    }

}
