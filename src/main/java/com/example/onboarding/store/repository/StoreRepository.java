package com.example.onboarding.store.repository;


import com.example.onboarding.store.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

    Optional<StoreEntity> findByStoreNumberAndUsageStatus(int storeNumber, boolean usageStatus);

   List<StoreEntity> findAllByUsageStatus(boolean usageStatus);
}
