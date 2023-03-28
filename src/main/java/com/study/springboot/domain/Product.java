package com.study.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.springboot.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    // 단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 양방향
    @ManyToOne(fetch = FetchType.LAZY)  // 얘도 양방향말고 단방향하면 안되나..? 오더에만 주문아이템 들어가게.- 아니다.오더가 생성되어야
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int product_price; // 주문 가격 - 할인 받을 수도 있으므로
    private int count; // 주문 수량

    // 생성 메소드 ===========================================
    public static Product createProduct(Cart cart, Item item, int product_price, int count) {
        Product product = new Product();
        product.cart = cart;
        product.item = item;
        product.product_price = product_price;
        product.count = count;
        return product;
    }

    public void addCount(int count) {
        this.count += count;
    }

}
