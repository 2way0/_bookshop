package com.study.springboot.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장될 수 있다.
@Getter  // 값타입. 생성할 때만 값이 셋팅되고 Setter 제공하지 않음.
public class Address {
    private String city;
    private String detailed_addr;
    private String zipcode;

    public Address() {  // jpa 스펙상 만든 거구나. 손 대지 말자. 함부로 생성하지 못하게.
    }

    public Address(String city, String detailed_addr, String zipcode) {
        this.city = city;
        this.detailed_addr = detailed_addr;
        this.zipcode = zipcode;
    }
}
