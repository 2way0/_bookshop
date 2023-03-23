package com.study.springboot.repository;

import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 등록
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    // 한 건 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //  도서 여러 건 조회
    public List<Book> findBookAll(){
        return em.createNativeQuery("select * from item where dtype = 'B'", Book.class).getResultList();
    }

    public List<Item> findBookAll2(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }


}
