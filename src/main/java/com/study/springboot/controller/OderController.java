package com.study.springboot.controller;

import com.study.springboot.controller.form.CartProductForm;
import com.study.springboot.domain.Product;
import com.study.springboot.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OderController {

    private final CartService cartService;


    @GetMapping("/myCart")
    public String myCart(Model model, HttpSession session) {
        Long member_id = (Long) session.getAttribute("member_id");
        List<Product> products = cartService.memberCartCheck(member_id);
        List<CartProductForm> cartProductForms = new ArrayList<>();
        int cartTotalPrice = 0;
        for (Product product : products) {
            Long id = product.getItem().getId();
            String name = product.getItem().getItem_name();
            int price = product.getProduct_price();
            int count = product.getCount();
            int total_price =  product.getProduct_price() * product.getCount();
            cartTotalPrice += total_price;
            CartProductForm cartProduct = new CartProductForm(id, name, price, count, total_price);
            cartProductForms.add(cartProduct);
        }

        model.addAttribute("cartList", cartProductForms);
        model.addAttribute("cartTotalPrice", cartTotalPrice);
        return "/order/cart";
    }

//    @GetMapping("/myCart")
//    public String myCart(Model model, HttpSession session) {
//        Long member_id = (Long) session.getAttribute("member_id");
//        List<Product> products = cartService.memberCartCheck(member_id);
//        int cartTotalPrice = 0;
//        for (Product product : products) {
//            cartTotalPrice +=  product.getProduct_price() * product.getCount();
//        }
//        model.addAttribute("cartList", products);
//        model.addAttribute("cartTotalPrice", cartTotalPrice);
//        return "/order/cart";
//    }

    @PostMapping("/addCart")
    @ResponseBody
    public String addCart(Long item_id, int count, HttpSession session){
        Long member_id = (Long) session.getAttribute("member_id");
        log.info("item_id+count+member_id================================="+item_id+"======"+count+"======"+member_id);
        cartService.addCart(member_id,item_id,count);
        return "장바구니 담기 성공";
    }



}
