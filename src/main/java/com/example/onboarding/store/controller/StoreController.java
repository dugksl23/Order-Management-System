package com.example.onboarding.store.controller;


import com.example.onboarding.order.controller.OrderController;
import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{store-number}")
    public ResponseEntity<?> fetchStore(@PathVariable("store-number") int storeNumber) {

        StoreResponseDto storeResponseDto = storeService.fetchStore(storeNumber);

        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<?> fetchAll() {

        List<StoreResponseDto> storeResponseList = storeService.fetchAll();

        return new ResponseEntity<>(storeResponseList, HttpStatus.OK);

    }


    @PostMapping("")
    public ResponseEntity<?> registerStore(@RequestBody @Valid StoreRequestDto storeRequestDto) {

        int storeNumber = storeService.registerStore(storeRequestDto);

        return new ResponseEntity<>(storeNumber, HttpStatus.OK);

    }

    @PutMapping("/{store-number}")
    public ResponseEntity<?> updateStore(@PathVariable("store-number") int storeNumber, @RequestBody StoreRequestDto storeRequestDto) {

        StoreResponseDto storeResponseDto = storeService.updateStore(storeNumber, storeRequestDto);

        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("{store-number}")
    public ResponseEntity<?> deleteStore(@PathVariable("store-number") int storeNumber) {

        storeService.deleteStore(storeNumber);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
