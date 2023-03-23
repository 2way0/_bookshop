package com.study.springboot.service;

import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import com.study.springboot.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 등록
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 변경 감지로 데이터 변경
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuntity) {
        Item findItem = itemRepository.findOne(itemId); // find로 찾아 온 findItem은 영속 상태. - 따라서 값을 세팅하면 @Transactional에 의해 커밋 됨. flush를 날림.
        findItem.change(itemId, name, price, stockQuntity);
    }

    public List<Book> findBooks() {
        return itemRepository.findBookAll();
    }

    public List<Item> findBooks2() {
        return itemRepository.findBookAll2();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
