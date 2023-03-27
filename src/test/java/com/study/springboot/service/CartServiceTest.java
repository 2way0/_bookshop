package com.study.springboot.service;

import com.study.springboot.domain.Address;
import com.study.springboot.domain.Cart;
import com.study.springboot.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    @Transactional
    void 장바구니는_가입할때_생성되므로_가입() {
       // Long member_id = memberService.join(new Member("cccart@cc","1234","d",new Address("d","d","d")));
        Cart cart = cartService.findCart(32L);
        log.info("회원가입시 장바구니 생성되는 거 확인 ==========="+cart.getId()+cart.getMember().getMember_email());

    }

}