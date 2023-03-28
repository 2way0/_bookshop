package com.study.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "products")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

//     양방향
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

//    // 양방향
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    private List<Product> products = new ArrayList<>();

//    // 양방향 get
//    public List<Product> getProducts() {
//        return products;
//    }


    // 생성 메서드 ====================================================================
    // 회원 가입시 생기기
    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        return cart;
    }


//        cart.orderItems.addAll(Arrays.asList(orderItems));


//    public void updateCart(Product... products) {
//        this.products = List.of(products);
//    }

}
