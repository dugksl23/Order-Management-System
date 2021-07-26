package com.example.onboarding.orderTest.repositoryTest;


import com.example.onboarding.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryTest extends JpaRepository<OrderEntity, Integer> {

}
