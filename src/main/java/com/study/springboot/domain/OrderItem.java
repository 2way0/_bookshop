package com.study.springboot.domain;

import com.study.springboot.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // OrderService 참조
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    // 단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

//    // 양방향
//    @ManyToOne(fetch = FetchType.LAZY)  // 얘도 양방향말고 단방향하면 안되나..? 오더에만 주문아이템 들어가게.- 아니다.오더가 생성되어야
//    @JoinColumn(name = "order_id")
//    private Order order;

    private int order_price; // 주문 가격 - 할인 받을 수도 있으므로
    private int count; // 주문 수량


    // 생성 메소드 ======================================================================================
    public static OrderItem createOrderItem(Item item, int order_price, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.order_price = order_price;
        orderItem.count = count;
        item.removeStock(count);
        return orderItem;
    }

    // 비즈니스 로직 ==================================================================================
    public void cancel() {
        getItem().addStock(count);
    }

    // 조회 로직 =====================================================================================
    // 주문상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrder_price() * getCount();
    }

    // 연관관계 편의 위해 ====


//    public void setOrder(Order order) {
//        this.order = order;
//    }
}
