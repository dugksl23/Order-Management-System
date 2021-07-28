package com.example.onboarding.scenarioTest;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.order.dto.OrderRequestDto;
import com.example.onboarding.order.entity.OrderEntity;
import com.example.onboarding.order.repository.OrderRepository;
import com.example.onboarding.order.service.OrderService;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.entity.StoreEntity;
import com.example.onboarding.store.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Scanner;

import static java.lang.String.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class Scenario {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreService storeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    @DisplayName("MockMvc 객체 DI 및 UTF 설정")
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

    }

    @Test
    @DisplayName("주문을 넣는 Scenario")
    @Transactional
    public void registerOrder() throws Exception {
        // 1. 주문
        System.out.println("어느 가게에서 백반을 주문하시겠습니까?");
        System.out.println("" +
                "1. 백종원의 백반집 0" +
                "2. 백종원의 백반집 1" +
                "3. 백종원의 백반집 2" +
                "4. 백종원의 백반집 3" +
                "5. 백종원의 백반집 4" +
                "6. 백종원의 백반집 5" +
                "7. 백종원의 백반집 6" +
                "8. 백종원의 백반집 7" +
                "9. 백종원의 백반집 8" +
                "10. 백종원의 백반집 9"
        );

        int storeNumber = 1;

        // 2. 선택된 식당 정보를 조회
        StoreEntity storeEntity = findStore(storeNumber);
        // 3. 식당 별 주문 등록
        OrderRequestDto orderRequestDto = new OrderRequestDto("VISA", UsageStatusConfiguration.USAGE_STATUS);
        int orderNumber = orderService.registerOrder(orderRequestDto);
        OrderEntity orderEntity = orderService.findByOrderNumber(orderNumber);
        orderEntity.setStore(storeEntity);
        orderRepository.save(orderEntity);

    }

    @Test
    @DisplayName("식당별 특정 주문을 조회하는 Scenario")
    @Transactional //영속성을 이어주기 위해서 Transactional을 사용한다.
    public void fetchOrder() throws Exception {
        // 1. 주문
        System.out.println("어느 가게의 주문을 조회하시겠습니까?");
        System.out.println("" +
                "1. 백종원의 백반집 0" +
                "2. 백종원의 백반집 1" +
                "3. 백종원의 백반집 2" +
                "4. 백종원의 백반집 3" +
                "5. 백종원의 백반집 4" +
                "6. 백종원의 백반집 5" +
                "7. 백종원의 백반집 6" +
                "8. 백종원의 백반집 7" +
                "9. 백종원의 백반집 8" +
                "10. 백종원의 백반집 9"
        );

        int storeNumber = 1;

        // 2. 선택된 식당 정보를 조회
        StoreEntity storeEntity = findStore(storeNumber);

        // 3. 식당 별 특정 주문 조회
        storeEntity.getOrders().forEach(num ->{
            System.out.println("1번 식당의 주문 : "+num.getOrderNumber());
        });

    }



    @DisplayName("식당 정보 조회")
    public StoreEntity findStore(int storeNumber) throws Exception {

        StoreEntity storeEntity = storeService.findByStoreNumber(storeNumber);
        return storeEntity;

    }


    //@Test
    @DisplayName("주문 조회 Test")
    public void fetchOrderTest() throws Exception {
        // given...
        int orderNumber = 11;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/order/" + orderNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        System.out.println(content);

    }

    //@Test
    @DisplayName("식당 정보 조회 Test")
    public void findStoreTest() throws Exception {

        // given...
        int storeNumber = 1;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/store/" + storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);

    }



}
