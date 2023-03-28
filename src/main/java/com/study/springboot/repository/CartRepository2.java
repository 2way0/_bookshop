package com.study.springboot.repository;

import com.study.springboot.domain.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CartRepository2 {

    private final EntityManager em;

    // 장바구니 저장
    public void save(Cart cart) {
        em.persist(cart);
    }

    // 장바구니 조회 (member_id 로)
    public List<Cart> findCart(Long member_id) {
        log.info("============================장바구니 조회 member_Id : " + member_id);
//        return em.createQuery("select c from Cart c where c.member.id = :member_id", Cart.class).getSingleResult();
        return em.createNativeQuery("select c from Cart c where c.member_id = :member_id", Cart.class).getResultList();
    }

}
