package com.study.springboot.domain.item;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
//@ToString
public class Book extends Item {

    private String author;
    private String isbn;
    private String publisher;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + super.getId() +
                ", item_name='" + super.getItem_name() + '\'' +
                ", item_price=" + getItem_price() +
                ", stock_quantity=" + getStock_quantity() +
                '}' +
                "Book{" +
                "author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';

    }
}
