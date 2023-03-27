package com.study.springboot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    // 단방향
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 단방향
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", member=" + member.getId() +
                ", orderItems=" + orderItems.stream().map(OrderItem::getOrder_price) +
                '}';
    }


    // 생성 메서드 ====================================================================
    // 회원 가입시 생기기
    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
//        cart.orderItems.addAll(Arrays.asList(orderItems));
        return cart;
    }

    public void updateCart(OrderItem... orderItems) {
        this.orderItems = List.of(orderItems);
    }
}
