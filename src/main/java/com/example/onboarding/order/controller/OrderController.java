package com.example.onboarding.order.controller;


import com.example.onboarding.order.dto.OrderRequestDto;
import com.example.onboarding.order.dto.OrderResponseDto;
import com.example.onboarding.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{order-number}")
    public ResponseEntity<?> fetchOrder(@PathVariable("order-number") int orderNumber) {

        OrderResponseDto orderResponseDto = orderService.fetchOrder(orderNumber);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<?> fetchAll() {

        List<OrderResponseDto> orderResponseList = orderService.fetchAll();

        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);

    }

    @PostMapping(value = "/store/{store-number}")
    public ResponseEntity<?> registerOrder(@RequestBody @Valid OrderRequestDto requestDto, @PathVariable("store-number") int storeNumber) {

        int orderNumber = orderService.registerOrder(requestDto, storeNumber);

        return new ResponseEntity<>(orderNumber, HttpStatus.OK);

    }

    @DeleteMapping("/{order-number}")
    public ResponseEntity<?> deleteOrder(@PathVariable("order-number") int orderNumber) {

        orderService.deleteOrder(orderNumber);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
