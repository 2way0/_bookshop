package com.study.springboot.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String member_email;
    private String member_pw;
    private String member_name;

    @Embedded
    private Address address;

    public Member(String member_email, String member_pw, String member_name, Address address) {
        this.member_email = member_email;
        this.member_pw = member_pw;
        this.member_name = member_name;
        this.address = address;
    }

    public Member() {

    }

}
