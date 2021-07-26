package com.example.onboarding.order.controller;


import com.example.onboarding.order.dto.OrderRequestDto;
import com.example.onboarding.order.dto.OrderResponseDto;
import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@NoArgsConstructor
public class OrderController {

    private OrderService orderService;

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


    @PostMapping("/order")
    public ResponseEntity<?> registerOrder(@ModelAttribute("order") OrderRequestDto orderDto) {

        int orderNumber = orderService.registerOrder(orderDto);
        return new ResponseEntity<>(orderNumber, HttpStatus.OK);

    }

    @DeleteMapping("/order/{order-number}")
    public ResponseEntity<?> deleteOrder(@PathVariable("order-number") int orderNumber) {

        orderService.deleteOrder(orderNumber);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
