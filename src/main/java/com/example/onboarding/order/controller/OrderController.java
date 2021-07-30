package com.example.onboarding.order.controller;


import com.example.onboarding.order.dto.OrderRequestDto;
import com.example.onboarding.order.dto.OrderResponseDto;
import com.example.onboarding.order.service.OrderService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<OrderResponseDto> fetchOrder(@PathVariable("order-number") int orderNumber) {

        OrderResponseDto orderResponseDto = orderService.fetchOrder(orderNumber);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<?> fetchAll() {

        List<OrderResponseDto> orderResponseList = orderService.fetchAll();

        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);

    }

    @PostMapping(value = "/store/{store-number}")
    public ResponseEntity<Integer> registerOrder(@RequestBody @Valid OrderRequestDto requestDto, @PathVariable("store-number") int storeNumber) {

        int orderNumber = orderService.registerOrder(requestDto, storeNumber);

        return ResponseEntity.ok(orderNumber);
        //return new ResponseEntity<>(orderNumber, HttpStatus.OK);
        // insert와 관련된 response value, 등록된 정보가 client에 필요한 상황에 따라서 dto를 return but client에서 내려주지 말라는 전제가 있지 않는 한 대부분 insert와 관련된 값으로 값을 return
        // insert, update 별 dto 생성이 필요.



    }

    @DeleteMapping("/{order-number}")
    public ResponseEntity<?> deleteOrder(@PathVariable("order-number") int orderNumber) {

        orderService.deleteOrder(orderNumber);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
