package com.example.onboarding.order.service;


import com.example.onboarding.order.dto.OrderRequestDto;
import com.example.onboarding.order.dto.OrderResponseDto;
import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public OrderResponseDto fetchOrder(int orderNumber) {

        OrderEntity orderEntity = orderRepository.findById(orderNumber).orElseThrow(() -> new NullPointerException("조회해온 정보가 없습니다."));
        return new OrderResponseDto(orderEntity);
    }

    public List<OrderResponseDto> fetchAll() {

        return orderRepository.findAll().stream()
                .map(OrderResponseDto::new).collect(Collectors.toList());
                //객체 참조를 통해 생성자에 OrderEntity가 각각 하나씩 들어간다.

    }

    @Transactional
    public int registerOrder(OrderRequestDto orderDto) {

        OrderEntity orderEntity = orderDto.toEntity();
        OrderEntity response = orderRepository.save(orderEntity);
        return response.getOrderNumber();
    }

    @Transactional
    public void deleteOrder(int orderNumber) {

        orderRepository.deleteOrderStatus(orderNumber);

    }


}
