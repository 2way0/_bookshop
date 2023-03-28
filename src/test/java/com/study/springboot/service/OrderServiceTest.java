package com.study.springboot.service;

import com.study.springboot.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void 주문하기(){
        // 12번 회원 1번 상품 2개 주문
        int orderTotalPrice = orderService.order(1L, 1L, 2);
        log.info("주문 총 가격=============="+orderTotalPrice);

    }

//    @Test
//    void 회원_주문_리스트() {
//        List<Order> order = orderService.findOrderList(10L);
//    }

}