package com.study.springboot.domain.item;

import com.study.springboot.domain.CategoryItem;
import com.study.springboot.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
//@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // 싱글테이블전략
@DiscriminatorColumn(name = "dtype")  // 구분될 때 Book이면 ~~할거야 이렇게 나오는 것.
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String item_name;
    private int item_price;
    private int stock_quantity;

    public Item() {

    }

    // 비즈니스 로직
    // stock 증가
    public void addStock(int quantity) {
        this.stock_quantity += quantity;
    }
    // stocck 감소 - 0보다 작아지면 안 됨.
    public void removeStock(int quantity) {
        int restStock = this.stock_quantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다");
        }
        this.stock_quantity = restStock;
    }

    public void change(Long item_id, String item_name, int item_price, int stock_quantity) {
        this.id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.stock_quantity = stock_quantity;
    }

    public Item( String item_name, int item_price, int stock_quantity) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.stock_quantity = stock_quantity;
    }

}
