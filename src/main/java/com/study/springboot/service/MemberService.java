package com.study.springboot.service;

import com.study.springboot.controller.form.LoginForm;
import com.study.springboot.domain.Member;
import com.study.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        // 중복회원 검증
        validationDuplicateMember(member.getMember_email());
        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원검증 메소드
    public void validationDuplicateMember(String member_email) {
        // 중복회원이면 EXCEPTION
        List<Member> findMembers = memberRepository.findOneEmail(member_email);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            // 실제론 수를 세서 0보다 크면 문제가 있다고 하는 것이 더 최적화
        }
    }

    // 로그인 메소드
    public Long login(LoginForm loginForm) {
        List<Member> member = memberRepository.findOneEmail(loginForm.getMember_email());
        if (member.size() == 1) {
            if (member.get(0).getMember_pw().equals(loginForm.getMember_pw())) {
                return member.get(0).getId();
            } else {
                return 0L;
            }
        }
        return 0L;
    }


    // 내 정보
    public Member myInfoData(Long member_id) {
        return memberRepository.findOne(member_id);
    }




}
