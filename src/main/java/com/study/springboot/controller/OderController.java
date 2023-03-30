package com.study.springboot.controller;

import com.study.springboot.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OderController {

    private final CartService cartService;


    @GetMapping("/myCart")
    public String myCart() {
        return "/order/cart";
    }

    @PostMapping("/addCart")
    @ResponseBody
    public String addCart(Long item_id, int count, HttpSession session){
        Long member_id = (Long) session.getAttribute("member_id");
        log.info("item_id+count+member_id================================="+item_id+"======"+count+"======"+member_id);
        cartService.addCart(member_id,item_id,count);
        return "장바구니 담기 성공";
    }

}
