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
@RequiredArgsConstructor //생성자 주입방식
//noArgs, allargs -> 코드 만드는 용도이지, di 안함
public class OrderController {

    private final OrderService orderService;
    private Logger logger = LoggerFactory.getLogger(OrderController.class);


    @GetMapping("/{order-number}")
    public ResponseEntity<?> fetchOrder(@PathVariable("order-number") int orderNumber) {

        OrderResponseDto response = orderService.fetchOrder(orderNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/orderList")
    public ResponseEntity<?> fetchAll() {

        List<OrderResponseDto> response = orderService.fetchAll();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PostMapping(value = "")
    public ResponseEntity<?> registerOrder(@RequestBody @Valid OrderRequestDto orderDto) {

        int orderNumber = orderService.registerOrder(orderDto);
        return new ResponseEntity<>(orderNumber, HttpStatus.OK);

    }

    @DeleteMapping("/{order-number}")
    public ResponseEntity<?> deleteOrder(@PathVariable("order-number") int orderNumber) {
        System.out.println("orderNumber : " + orderNumber);
        orderService.deleteOrder(orderNumber);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
