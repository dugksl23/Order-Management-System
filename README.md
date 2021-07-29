# Order-Management-System

## made by 김 요한
<br>


# 목차
#### 1. 프로젝트 소개
#### 2. 개발 환경
#### 3. API 기능 요약
#### 4. 시나리오 설명

---

## 1. 프로젝트 소개

스프링과 JPA를 활용한 식당 관리 및 주문 현황을 확인할 수 있는 간단한 서비스 구현.

## 2. 개발 환경

* **Java EE IDE** 
  * Intelli J

* **DB** 
  * H2 DB
  
* **개발 언어** 
  * Java (JDK 1.8)  
  
* **프레임워크**
  * Spring Boot gradle
  
* **형상관리**
  * GitHub
  
  
 
## 3. API 기능 요약

#### 1. **게시판**
 - Store
    1) Store 정보 등록
    2) Store 개별 조회
    3) Store 전체 조회
    4) Store 정보 수정
    5) Store 정보 삭제

 - Order
    1) Order 정보 등록
    2) Order 개별 조회
    3) Order 전체 조회
    4) Order 정보 삭제


## 4. 시나리오 설명

1) 10건의 Store 정보를 등록
<br>

```
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

```

2) 1벅 식당에서 주문
<br>

```
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

```

3) 1번 식당에서 주문한 주문 번호를 조회
<br>

```
    @DisplayName("주문 조회 Scenario")
    @Transactional
    public void fetchOrderTest() throws Exception {

        // given...
        int orderNumber = 1;

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

```

4) 1번 주문 번호 삭제
<br>

```
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

```

5) 식당 정보 개별 조회
<br>

```
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

```

6) 식당 정보 수정
<br>

```
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

```


7) 식당 정보 삭제
<br>

```
    @DisplayName("식당 정보 삭제 Test")
    public void deleteStoreTest() throws Exception {

        // given...
        int storeNumber = 2;

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


```

8) 식당 전체 조회
<br>

```
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

```
