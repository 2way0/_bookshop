package com.study.springboot.service;

import com.study.springboot.domain.item.Album;
import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void 상품등록2() {
        itemService.saveItem(new Book("책2",800,600,"작가2","2V","출판사2"));
    }

    @Test
    void 상품등록() {
        itemService.saveItem(new Book("책1",500,500,"작가1","1V","출판사1"));
    }


    @Test
    void 도서리스트_가져오기(){

        List<Book> books = itemService.findbooks();
        log.info("==================아이템들======="+books.toString());

    }

    @Test
    void 음반리스트_가져오기(){

        List<Album> albums = itemService.findAlbums();
        log.info("==================아이템들======="+albums.toString());

    }


    @Test
    void 도서_한건_가져오기(){

        Item book = itemService.findOne(1L);
        log.info("==================아이템 이름======="+book.getItem_name());
        log.info("==================아이템 타입 확인======="+ book.getClass().equals(Book.class));

    }






}