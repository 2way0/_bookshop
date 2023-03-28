package com.study.springboot.repository;

import com.study.springboot.domain.Cart;
import com.study.springboot.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


//
//    // 장바구니 저장
//
//    public void save(Cart cart) {
//        em.persist(cart);
//    }


//    @Query(value = "select * from cart  where member_id = :member_id",nativeQuery = true)



    Cart findCartByMember_id(Long member_id);









}
