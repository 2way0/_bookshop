package com.study.springboot.controller.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@ToString
public class LoginForm {

    @Email
    @NotEmpty(message = "이메일을 입력하세요")  // 값이 비어있으면 오류가 남.
    private String member_email;

    @NotEmpty(message = "비밀번호를 입력하세요")
    private String member_pw;

    public LoginForm(String member_email, String member_pw) {
        this.member_email = member_email;
        this.member_pw = member_pw;
    }

    public LoginForm(){
    }
}
