package com.study.springboot.controller.form;

import com.study.springboot.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @Email
    @NotEmpty(message = "회원 이메일 입력은 필수 입니다.")  // 값이 비어있으면 오류가 남.
    private String member_email;

    @NotEmpty(message = "회원 비밀번호 입력은 필수 입니다.")
    private String member_pw;

    @NotEmpty(message = "회원 비밀번호 확인 입력은 필수 입니다.")
    private String member_pw_check;

    @NotEmpty(message = "회원 닉네임 입력은 필수 입니다.")
    private String member_name;

    private String city;
    private String detailed_addr;
    private String zipcode;

    public MemberForm(String member_email, String member_pw, String member_pw_check, String member_name, String city, String detailed_addr, String zipcode) {
        this.member_email = member_email;
        this.member_pw = member_pw;
        this.member_pw_check = member_pw_check;
        this.member_name = member_name;
        this.city = city;
        this.detailed_addr = detailed_addr;
        this.zipcode = zipcode;
    }

    //    private Address member_address;

//    public MemberForm(String member_email, String member_pw, String member_pw_check, String member_name, Address member_address) {
//        this.member_email = member_email;
//        this.member_pw = member_pw;
//        this.member_pw_check = member_pw_check;
//        this.member_name = member_name;
//        this.member_address = member_address;
//    }

    // Validation 용
    public MemberForm() {
    }




}
