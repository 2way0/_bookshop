package com.study.springboot.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
//@ToString
@NoArgsConstructor
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


    public Book( String item_name, int item_price, int stock_quantity, String author, String isbn, String publisher) {
        super(item_name, item_price, stock_quantity);
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
    }
}
