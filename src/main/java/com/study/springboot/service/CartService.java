package com.study.springboot.service;

import com.study.springboot.domain.Cart;
import com.study.springboot.domain.Member;
import com.study.springboot.domain.OrderItem;
import com.study.springboot.domain.Product;
import com.study.springboot.domain.item.Book;
import com.study.springboot.domain.item.Item;
import com.study.springboot.repository.CartRepository;
import com.study.springboot.repository.ItemRepository;
import com.study.springboot.repository.MemberRepository;
import com.study.springboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final EntityManager em;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;


    // 장바구니에 상품추가
    @Transactional
    public void addCart (Long member_id, Long item_id, int count) {
        Cart cart = cartRepository.findCartByMember_id(member_id);
        Item item = itemRepository.findItemById(item_id);

        List<Product> existProduct = productRepository.selectCartProducts(cart.getId(), item_id);
        if (!existProduct.isEmpty()) {
            Long product_id = existProduct.get(0).getId();
            Product product = productRepository.findProductById(product_id);
            product.addCount(count);
        } else {
            Product product = Product.createProduct(cart, item, item.getItem_price(), count);
            em.persist(product);

        }
//        cart.getProducts().add(product);
//        cartRepository.save(cart);
//        return cart.getProducts().size();
    }

    // 장바구니 불러오기
    public Cart findCart(Long member_id) {
        return cartRepository.findCartByMember_id(member_id);
    }


//    @Transactional
//    public int order(Long member_id, Long item_id, int count) {
//        Member member = memberRepository.findOne(member_id);
//        Item item = itemRepository.findOne(item_id);
//        OrderItem orderItem = OrderItem.createOrderItem()
//    }


//    public List<Product> existProduct(Long cart_id, Long item_id) {
//        return productRepository.selectCartProducts(cart_id, item_id);
//    }


}
