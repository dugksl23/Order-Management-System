package com.example.onboarding.store.controller;


import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{store-number}")
    public ResponseEntity<?> findStore(@PathVariable("store-number") int storeNumber) {
        StoreResponseDto storeResponseDto = storeService.findStore(storeNumber);
        System.out.println("storeNumber : "+storeResponseDto);

        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);

    }

    @GetMapping("/storeList")
    public ResponseEntity<?> fetchAll() {

        List<StoreResponseDto> storeResponseList = storeService.fetchAll();
        return new ResponseEntity<>(storeResponseList, HttpStatus.OK);

    }


    @PostMapping("")
    public ResponseEntity<?> registerStore(@RequestBody StoreRequestDto requestDto) {

        int storeNumber = storeService.registerStore(requestDto);
        return new ResponseEntity<>(storeNumber, HttpStatus.OK);

    }

    @PutMapping("/updateStore")
    public ResponseEntity<?> updateStore(@Valid StoreRequestDto requestDto) {

        storeService.updateStore(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("{store-number}")
    @Transactional
    public ResponseEntity<?> deleteStore(@PathVariable("store-number") int storeNumber) {

        storeService.deleteStore(storeNumber);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
