package com.study.springboot.controller;

import com.study.springboot.domain.item.Album;
import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import com.study.springboot.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/bookList")
    public String bookList(Model model) {
        List<Book> bookList = itemService.findbooks();
        model.addAttribute("itemList", bookList);
        model.addAttribute("item", "도서");
        return "item/itemList";
    }

    @GetMapping("/albumList")
    public String albumList(Model model) {
        List<Album> bookList = itemService.findAlbums();
        model.addAttribute("itemList", bookList);
        model.addAttribute("item", "음반");
        return "item/itemList";
    }

    @GetMapping("/itemDetail")
    public String itemDetail(String item_id, String item_type, Model model) {
        log.info("itemDetail====아이템 번호 넘어옴======================"+item_id);
        log.info("itemDetail====아이템 타입 넘어옴======================"+item_type);

        if (item_type.equals("도서")) {
            Book book = (Book) itemService.findOne(Long.valueOf(item_id));
            log.info("itemDetail====아이템 번호 넘어옴======================"+book);
            model.addAttribute("itemDetail", book);
            model.addAttribute("item", "도서");
        } else if (item_type.equals("음반")) {
            Album album = (Album) itemService.findOne(Long.valueOf(item_id));
            log.info("itemDetail====아이템 번호 넘어옴======================"+album);
            model.addAttribute("itemDetail", album);
            model.addAttribute("item", "음반");
        }



        return "item/itemDetail";
    }






}
