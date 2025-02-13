package com.example.allramarket.domain.cart.service;

import com.example.allramarket.domain.cart.dto.CartItemDto;
import com.example.allramarket.domain.cart.entity.Cart;
import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.cart.repository.CartItemRepository;
import com.example.allramarket.domain.cart.repository.CartRepository;
import com.example.allramarket.domain.customer.repository.CustomerRepository;
import com.example.allramarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    //장바구니 생성
    @Transactional
    public void cartCreate(){

    }

    //장바구니 조회
    //cart id
    public List<CartItemDto> cartList(Long id){
        Cart cart = cartRepository.findByCustomer(id);
        List<CartItem> cartItem = cartItemRepository.findCartItemByCartAndId(cart.getId());
        List<CartItemDto> cartItemDto = cartItem.stream().map(map -> new CartItemDto(map)).collect(Collectors.toList());

        return cartItemDto;
    }

    //장바구니 추가
    @Transactional
    public void cartAdd(){

    }


    //장바구니 수정
    @Transactional
    public void cartUpdate(){

    }

    //장바구니 삭제
    //cart item id
    @Transactional
    public void cartDelete(Long id){
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        Cart cart = cartItem.get().getCart();

        cartItemRepository.delete(cartItem.get());
    }
}
