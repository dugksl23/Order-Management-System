package com.example.onboarding.orderTest.controllerTest;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
import com.example.onboarding.order.controller.OrderController;
import com.example.onboarding.order.dto.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @BeforeEach
    @DisplayName("MockMvc 객체 DI 및 UTF 설정")
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }


    @Test
    @DisplayName("주문 생성 test")
    public void registerOrderTest() throws Exception {

        // given...
        OrderRequestDto requestDtoTest = new OrderRequestDto(0,"VISA", UsageStatusConfiguration.USAGE_STATUS);
        int storeNumber = 1;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/order/store"+storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoTest))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        logger.error("error {}", content);

    }

    //@Test
    @DisplayName("주문 (개별건) 조회 test")
    public void fetchOrderTest() throws Exception {

        // given...
        int orderNumber = 3;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/order/" + orderNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        logger.error("error {}", content);


    }

    //@Test
    @DisplayName("주문 List 조회 test")
    public void fetchAllTest() throws Exception {

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/order/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        logger.error("error {}", content);

    }

    //@Test
    @DisplayName("주문 삭제 test")
    public void deleteOrderTest() throws Exception {

        // given...
        int orderNumber = 1;


        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.delete("/order/" + orderNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        System.out.println("content : " + content);
        logger.error("error {}", content);

    }


}
