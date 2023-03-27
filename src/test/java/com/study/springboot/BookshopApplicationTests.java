package com.study.springboot;

import com.study.springboot.controller.form.LoginForm;
import com.study.springboot.domain.Address;
import com.study.springboot.domain.Member;
import com.study.springboot.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookshopApplicationTests {

    @Autowired
    private MemberService memberService;


    @Test
    void contextLoads() {
    }

    @Test
    void 회원가입(){
        memberService.join(new Member("cart@young","1234","d",new Address("d","d","d")));
    }


    @Test
    public void 중복_회원_예외2() throws Exception {
        //given

        //when

        //then
        assertThrows(IllegalStateException.class, ()-> {
            memberService.validationDuplicateMember("dd@dd"); // 똑같은 이름이므로 예외가 발생해야 한다. - 예외가 튀어 나와서 밖으로 나가야 한다.
        });
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 로그인(){
        // 회원가입
        Long testid = memberService.join(new Member("ttestdd@dd","1234","d",new Address("d","d","d")));
        System.out.println("testid = " + testid);

        Long loginId = memberService.login(new LoginForm("ttestdd@dd", "1234"));

        Assertions.assertEquals(loginId,testid);




    }
}
