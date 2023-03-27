package com.study.springboot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)   // OrderService 참조
@Table(name = "orders") // 테이블명으로 order 못 써서
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime order_date;

    // 단방향
    @ManyToOne(fetch = FetchType.LAZY)  // One으로 끝나는 건 기본이 EAGER라서 (fetch = FetchType.LAZY) 로 바꿔줘야 함.
    @JoinColumn(name = "member_id")
    private Member member;

    // 양방향 X
    // 단방향
    // Order가 Delivery를 관리하고 Order가 OrderItem을 관리할 때. 요 그림에서 cascade씀. - 참조하는 주인이 private 오너인 경우에만 씀 - Delivery랑 OrderItem은 Order만 참조해서 쓴다.
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 양방향
    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL) // cascade = CascadeType.ALL 한 이유 정리.
    // OneToOne 관계에서 외래키를 어디다 두느냐를 선택해야 하는데 보통 조회를 많이 하는 곳에 둔다 ( 이 경우 주문들어가서 배송을 보고 배송보고 주문들어갈 일이 없으므로 외래키를 Order테이블에 둔다, 연관관계 주인도 외래키가 있는 Order)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orders_date;  // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;   // 주문 상태


    // 연관관계 편의 메서드 (양방향 중 컨트롤하는 쪽이 들고 있는 게 좋음)=================================================

//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//      //  orderItem.setOrder(this);
//    }

    // 생성 메서드 ===========================================================================================
    // 정적 팩토리 메서드 static
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.member = member;
        order.delivery = delivery;
        order.orderItems.addAll(Arrays.asList(orderItems));
        order.status = OrderStatus.ORDER;
        order.order_date = LocalDateTime.now();
        return order;
    }

    public void setOrders_date(LocalDateTime orders_date) {
        this.orders_date = orders_date;
    }

    // 주문 취소 상태 변경
    private void cancelStatus() {
        this.status = OrderStatus.CENCLE;
    }


    // 비즈니스 로직 =======================================================================================

    // 주문 취소
    public void cancel() {
        // 배송돼서 취소가 불가 -- COMP : 배송완료
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.cancelStatus();  // 상태 취소로
        // 루프 돌면서 재고 원복
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    // 조회 로직 =====================================================================================

    // 전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
//        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }


}
