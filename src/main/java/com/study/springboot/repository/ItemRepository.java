package com.study.springboot.repository;

import com.study.springboot.domain.item.Album;
import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {


    // 한 건 조회
    Item findItemById(Long item_id);


    @Query(value = "select * from Item where dtype = 'B'",nativeQuery = true)
    List<Book> findBooks();

    @Query(value = "select * from Item where dtype = 'A'",nativeQuery = true)
    List<Album> findAlbums();


    // dtype조회





//    //  도서 여러 건 조회
//    public List<Book> findBookAll(){
//        return em.createNativeQuery("select * from item where dtype = 'B'", Book.class).getResultList();
//    }
//
//    public List<Item> findBookAll2(){
//        return em.createQuery("select i from Item i", Item.class).getResultList();
//    }


}
