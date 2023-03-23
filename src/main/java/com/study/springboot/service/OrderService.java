package com.study.springboot.service;

import com.study.springboot.domain.Delivery;
import com.study.springboot.domain.Member;
import com.study.springboot.domain.Order;
import com.study.springboot.domain.OrderItem;
import com.study.springboot.domain.item.Item;
import com.study.springboot.repository.ItemRepository;
import com.study.springboot.repository.MemberRepository;
import com.study.springboot.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getItem_price(), count);
        // 아래 처럼 주문 상품 생성하면 분산되니까 유지보수하기 힘들다. 따라서 위의 로직을 제외한 생성을 막아야한다. -  OrderItem 클래스에 가서 기본생성자를 protected로 만듦. - 그럼 new 할 때 빨간불 뜨게 돼서 생성 막아줌.
//        OrderItem orderItem1 = new OrderItem();
//        orderItem1.setCount();

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

}
