package com.study.springboot.repository;

import com.study.springboot.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    //주문 조회 (주문 번호로)
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 주문 조회 (member_id로)
    public List<Order> findOrderList(Long member_id) {
        log.info("============================주문 조회 member_Id : " + member_id);
        return em.createQuery("select o from orders o where o.member_id = :member_id", Order.class).getResultList();
    }



}
