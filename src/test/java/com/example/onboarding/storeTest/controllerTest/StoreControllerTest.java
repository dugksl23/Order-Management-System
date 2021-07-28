package com.example.onboarding.storeTest.controllerTest;


import com.example.onboarding.store.dto.StoreRequestDto;
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
        StoreRequestDto storeRequestDtoTest = new StoreRequestDto("백종원의 백반집", 2233, "종로구", false);

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

        // @ModelAttribute는 request의 Body의 값을 dto 객체로 바인딩하지 못한다.
        // 파라미터로 값으로 dto객체의 field에 바인딩을 하는 방식이다. 또한 dto에는 setter가 있어야 한다.
        // 반면, RequestBody는 post의 body에 json이나, xml 값을 java의 object로 messageConverter를 반드시 거쳐 dto타입으로 바꿔서 바인딩한다.

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
        System.out.println(content);

        //int storeNumberExpected = Integer.parseInt(parser.parse(content).getAsJsonObject().get("storeNumber").toString());
        //Assert.assertEquals("number : ",storeNumberExpected, storeNumber);

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

        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        System.out.println(content);

    }

    //@Test
    @DisplayName("식당 정보 삭제 Test")
    public void deleteStoreTest() throws Exception {

        // given...
        int storeNumber = 1;

        // when...
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.delete("/store/"+storeNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        System.out.println(content);

    }

}
