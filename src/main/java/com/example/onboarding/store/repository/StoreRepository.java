package com.example.onboarding.store.repository;


import com.example.onboarding.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

    Optional<StoreEntity> findByStoreNumberAndUsageStatus(int storeNumber, boolean usageStatus);

    List<StoreEntity> findAllByUsageStatus(boolean usageStatus);

    @Modifying
    @Query(value = "UPDATE StoreEntity s set s.usageStatus = true where s.storeNumber = :storeNumber")
    void updateUsageStatus(int storeNumber);

}
