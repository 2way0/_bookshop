package com.study.springboot.service;

import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void 도서리스트_가져오기(){

        List<Book> bookList = itemService.findBooks();
        log.info("==================================================도서체크"+bookList.get(0));
    }

    @Test
    void 도서_한건_가져오기(){

    }
}