package com.study.springboot.repository;

import com.study.springboot.controller.form.LoginForm;
import com.study.springboot.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final EntityManager em;

    // 저장
    public void save(Member member) {em.persist(member);}

    // 아이디로 한 건 불러오기
    public Member findOne(Long id) {return em.find(Member.class, id);}

    // 이메일로 불러오기
    public List<Member> findOneEmail(String member_email) {
        log.info("============================email : "+member_email);
        List<Member> du = em.createQuery("select m from Member m where m.member_email = :member_email", Member.class).setParameter("member_email", member_email).getResultList();
//        log.info("============================email-du : "+du.get(0).getMember_email());
        return du;
    }

//    // 로그인 확인
//    public Member loginCheck(String member_email) {
//        log.info("============================email : "+member_email);
//
//        Member du = em.createQuery("select m from Member m where m.member_email = :member_email", Member.class).setParameter("member_email", member_email).getSingleResult();
////        log.info("============================email-du : "+du.get(0).getMember_email());
//        if (du.equals(null){
//            return
//
//        }
//        return du;
//    }
}
