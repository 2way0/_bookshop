package com.study.springboot.service;

import com.study.springboot.domain.Address;
import com.study.springboot.domain.Cart;
import com.study.springboot.domain.Member;
import com.study.springboot.domain.Product;
import com.study.springboot.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CartServiceTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartService cartService;

    @Autowired
    MemberService memberService;

    @Test
//    @Transactional
//    @Rollback(value = false)
    void 장바구니는_가입할때_생성_확인() {
       // Long member_id = memberService.join(new Member("cart0327@cc","1234","d",new Address("d","d","d")));
        Cart cart = cartService.findCart(1L);
        log.info("회원가입시 장바구니 생성되는 거 확인 ==========="+cart.getId());

    }

//    @Test
//    void 장바구니_상품추가() {
//        cartService.addCart(1L, 1L, 3);
//    }


    @Test
    void 장바구니_상품추가2() {
        cartService.addCart(1L, 2L, 2);
    }

//    @Test
//    void 확인() {
//        List<Product> product = cartService.productCart(1L,1L);
//        log.info("dddddddddddddddddddddddd"+product.get(0).getId());
//    }
}