package com.example.onboarding.scenarioTest;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.order.controller.OrderController;
import com.example.onboarding.order.dto.OrderRequestDto;
import com.example.onboarding.store.dto.StoreRequestDto;
import com.example.onboarding.store.dto.StoreResponseDto;
import com.example.onboarding.store.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @BeforeEach
    @DisplayName("MockMvc 객체 DI 및 UTF 설정")
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

    }

    //@Test
    @DisplayName("식당 정보 등록 Test")
    public void registerStoreTest() throws Exception {

        // given...
        for (int i = 0; i < 10; i++) {

            StoreRequestDto storeRequestDtoTest = new StoreRequestDto(0, "백종원의 백반집" + 10, 2233, "종로구", UsageStatusConfiguration.USAGE_STATUS);

            // when...
            MvcResult result = mvc.perform(
                    MockMvcRequestBuilders.post("/store")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(storeRequestDtoTest))
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            // then..
            String content = result.getResponse().getContentAsString();
            Assert.assertNotNull(content);
            logger.debug("debug, {}", content);

        }

    }


    //@Test
    @DisplayName("주문 등록 Scenario")
    @Transactional
    // 테스트 성공시에는 rollback을 지원
    public void registerOrderTest() throws Exception {

        // given...
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

        OrderRequestDto orderRequestDto = new OrderRequestDto(0, "VISA", UsageStatusConfiguration.USAGE_STATUS);
        int storeNumber = 1;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/order/store/" + storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequestDto))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();

        // then..
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }

    //@Test
    @DisplayName("주먼 조회 Scenario")
    @Transactional
    public void fetchOrderTest() throws Exception {

        // given...
        int orderNumber = 3;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/order/" + orderNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // then...
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }

    //@Test
    @DisplayName("모든 주문 조회 Test")
    @Transactional
    public void fetchAllOrderTest() throws Exception {

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/order/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();

        // then..
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }

    //@Test
    @DisplayName("식당 정보 조회 Test")
    public void fetchStoreTest() throws Exception {

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

        // then..
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }

    //@Test
    @DisplayName("모든 식당 정보 조회 Test")
    public void fetchAllStoreTest() throws Exception {

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/store/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // then..
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }

    //@Test
    @DisplayName("식당 정보 수정 test")
    public void updateOrderTest() throws Exception {

        // given...
        int storeNumber = 1;
        // view단에서 store의 data를 가지고 있다고 가정.
        StoreResponseDto storeResponseDto = storeService.fetchStore(storeNumber);
        storeResponseDto.setAddress("강남구");
        storeResponseDto.setName("백종원의 국밥집");


        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/store/" + storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storeResponseDto))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();


        // then..
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }

    @Test
    @DisplayName("주문 정보 삭제 Test")
    @Transactional
    public void deleteOrderTest() throws Exception {

        // given...
        int orderNumber = 3;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.delete("/order/" + orderNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();

        // then..
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.debug("debug, {}", content);

    }


}
