package com.study.springboot.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    // 양방향
//    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
//    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;  // READY, COMP


    // 연관관계 편의 위해
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    // 생성 메서드 ==============================================================================
    public Delivery(Address address) {
        this.address = address;
    }

    // 배송상태 변경
    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }



}
