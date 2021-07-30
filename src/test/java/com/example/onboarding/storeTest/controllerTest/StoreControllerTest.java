package com.example.onboarding.storeTest.controllerTest;


import com.example.onboarding.common.statics.UsageStatusConfiguration;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class StoreControllerTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StoreService storeService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @BeforeEach
    @DisplayName("MockMvc 객체 DI 및 UTF 설정")
    public void setup() {

        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

    }

    @Test
    @DisplayName("식당 정보 등록 Test")
    public void registerStoreTest() throws Exception {

        // given...
        for (int i = 0; i < 10; i++) {

            StoreRequestDto storeRequestDtoTest = new StoreRequestDto(0, "백종원의 백반집" + i, 2233, "종로구", UsageStatusConfiguration.USAGE_STATUS);

            // when...
            MvcResult result = mvc.perform(
                    MockMvcRequestBuilders.post("/store")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(storeRequestDtoTest))
                            //.param("name",name)
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk())
                    //.andExpect(jsonPath("name").value(name))
                    .andDo(print())
                    .andReturn();

            // then...
            String content = result.getResponse().getContentAsString();
            org.springframework.util.Assert.notNull(content);
            logger.error("error {}", content);

        }

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

        // then...
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.error("error {}", content);

    }

    //@Test
    @DisplayName("식당 정보 모두 조회 Test")
    public void fetchAllTest() throws Exception {

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/store/storeList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // then...
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.error("error {}", content);

    }

    //@Test
    @DisplayName("식당 정보 삭제 Test")
    public void deleteStoreTest() throws Exception {

        // given...
        int storeNumber = 1;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.delete("/store/" + storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // then...
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.error("error {}", content);

    }

    //@Test
    @DisplayName("식당 정보 수정 Test")
    public void updateStoreTest() throws Exception {

        // given...
        int storeNumber = 1;
        StoreResponseDto dto = storeService.fetchStore(storeNumber);
        //dto.setAddress("강남구");
        //dto.setName("백종원의 국밥집");

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/store/" + storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // then...
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        logger.error("error {}", content);

    }
    
}
