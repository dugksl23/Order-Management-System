package com.example.onboarding.store.controller;


import com.example.onboarding.store.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@NoArgsConstructor
@AllArgsConstructor
public class StoreController {

    private StoreService storeService;

    @GetMapping("/searchStoreInfo")
    public void searchStoreInfo(){

    }

    @GetMapping("/registerStoreInfo")
    public void registerStoreInfo(){

    }

    @PutMapping("/updateStoreInfo")
    public void updateStoreInfo(){

    }

    @DeleteMapping("/deleteStoreInfo")
    public void deleteStoreInfo(){

    }


}
