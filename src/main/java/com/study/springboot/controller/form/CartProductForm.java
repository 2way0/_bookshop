package com.study.springboot.controller.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CartProductForm {
    private Long item_id;
    private String item_name;
    private int item_price;
    private int count;
    private int total_item_price;

    public CartProductForm(Long item_id, String item_name, int item_price, int count, int total_item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.count = count;
        this.total_item_price = total_item_price;
    }
}
