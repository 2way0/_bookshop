package com.study.springboot.repository;

import com.study.springboot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "select * from product where cart_id = :cart_id and item_id = :item_id", nativeQuery = true)
    List<Product> existCartProducts(@Param("cart_id") Long cart_id,@Param("item_id") Long item_id);


    @Query(value = "select * from product where cart_id = :cart_id", nativeQuery = true)
    List<Product> memberCartCheck(@Param("cart_id") Long cart_id);

    Product findProductById(Long product_id);

}
