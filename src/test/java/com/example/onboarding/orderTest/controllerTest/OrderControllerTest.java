package com.example.onboarding.orderTest.controllerTest;


import com.example.onboarding.order.controller.OrderController;
import com.example.onboarding.order.dto.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@RunWith(SpringRunner.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Test
    @DisplayName("주문 생성 test")
    public void registerOrderTest() throws Exception {

        System.out.println("오나요?");
        // given...
        OrderRequestDto requestDtoTest = new OrderRequestDto(0, "YH", "백종원의 불백집", "종로구,", false);
        System.out.println(requestDtoTest.toString());

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/order/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDtoTest))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        logger.error("error {}", content);


    }

}
