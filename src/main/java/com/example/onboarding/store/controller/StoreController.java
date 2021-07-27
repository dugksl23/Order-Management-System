package com.example.onboarding.store.controller;


import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/store/{storeNumber}")
    public ResponseEntity<?> searchStoreInfo(int storeNumber) {

        StoreResponseDto storeResponseDto = storeService.searchStore(storeNumber);
        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);

    }

    @GetMapping("/storeList")
    public ResponseEntity<?> fetchAll() {

        List<StoreResponseDto> storeResponseList = storeService.fetchAll();
        return new ResponseEntity<>(storeResponseList, HttpStatus.OK);

    }


    @PostMapping("/store")
    public ResponseEntity<?> registerStore(@Valid StoreRequestDto requestDto) {

        int storeNumber = storeService.registerStore(requestDto);
        return new ResponseEntity<>(storeNumber, HttpStatus.OK);

    }

    @PutMapping("/updateStore")
    public ResponseEntity<?> updateStore(StoreRequestDto requestDto) {

        storeService.updateStore(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/deleteStore/{store-number}")
    public ResponseEntity<?> deleteStore(@PathVariable("store-number") int storeNumber) {

        storeService.deleteStore(storeNumber);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
