package com.study.springboot.service;

import com.study.springboot.domain.Cart;
import com.study.springboot.domain.Member;
import com.study.springboot.domain.OrderItem;
import com.study.springboot.domain.item.Item;
import com.study.springboot.repository.CartRepository;
import com.study.springboot.repository.ItemRepository;
import com.study.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    // 장바구니에 상품추가
    @Transactional
    public int addCart (Long member_id, Long item_id, int count) {
        Cart cart = cartRepository.findOne(member_id);
        Item item = itemRepository.findOne(item_id);
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getItem_price(), count);
        cart.getOrderItems().add(orderItem);
        return cart.getOrderItems().size();
    }

    // 장바구니 불러오기
    public Cart findCart(Long member_id) {
        return cartRepository.findOne(member_id);
    }


//    @Transactional
//    public int order(Long member_id, Long item_id, int count) {
//        Member member = memberRepository.findOne(member_id);
//        Item item = itemRepository.findOne(item_id);
//        OrderItem orderItem = OrderItem.createOrderItem()
//    }




}
