package com.example.onboarding.store.controller;


import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.service.StoreService;
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

    @GetMapping("/{store-number}")
    public ResponseEntity<?> fetchStore(@PathVariable("store-number") int storeNumber) {

        StoreResponseDto storeResponseDto = storeService.fetchStore(storeNumber);

        return new ResponseEntity<>(storeResponseDto, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<StoreResponseDto>> fetchAll() {

        List<StoreResponseDto> storeResponseList = storeService.fetchAll();

        return new ResponseEntity<>(storeResponseList, HttpStatus.OK);

    }

    @GetMapping("/join-list")
    public ResponseEntity<List<StoreResponseDto>> fetchAllWithInnerJoin() {

        List<StoreResponseDto> storeResponseList = storeService.findAllWithFetchJoin();

        return new ResponseEntity<>(storeResponseList, HttpStatus.OK);

    }

    @GetMapping("/graph-list")
    public ResponseEntity<List<StoreResponseDto>> fetchAllWithGraph() {

        List<StoreResponseDto> storeResponseList = storeService.findAllWithGraph();

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
