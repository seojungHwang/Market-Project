package com.example.allramarket.domain.cart.service;

import com.example.allramarket.domain.cart.dto.CartItemDto;
import com.example.allramarket.domain.cart.entity.Cart;
import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.cart.repository.CartItemRepository;
import com.example.allramarket.domain.cart.repository.CartRepository;
import com.example.allramarket.domain.customer.entity.Customer;
import com.example.allramarket.domain.customer.repository.CustomerRepository;
import com.example.allramarket.domain.product.entity.Product;
import com.example.allramarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public void cartCreate(Customer customer){
       Cart cart = Cart.builder()
               .customer(customer)
               .build();

       Cart save = cartRepository.save(cart);
    }

    //장바구니 조회
    //customer id
    public List<CartItemDto> cartList(Long id){
        Cart cart = Optional.ofNullable(cartRepository.findByCustomer(id))
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for customer ID: " + id));
        List<CartItem> cartItem = Optional.ofNullable(cartItemRepository.findCartItemByCartAndId(cart.getId()))
                .orElse(Collections.emptyList());

        List<CartItemDto> cartItemDto = cartItem.stream().map(CartItemDto::new).collect(Collectors.toList());

        return cartItemDto;
    }

    //장바구니 추가
    @Transactional
    public CartItem cartAdd(CartItemDto.CartItemAddDto cartItemAddDto){
       Optional<Customer> customer = customerRepository.findById(cartItemAddDto.getCustomerId());
       Cart cart = cartRepository.findByCustomer(customer.get().getId());
       Optional<Product> product = productRepository.findById(cartItemAddDto.getProductId());

       CartItem cartItem = CartItem.builder()
               .cart(cart)
               .count(cartItemAddDto.getCount())
               .product(product.get())
               .build();

        CartItem save = cartItemRepository.save(cartItem);

        return save;
    }


    //장바구니 수정
    @Transactional
    public CartItem cartUpdate(CartItemDto.CartItemUpdateDto cartItemUpdateDto){
        Optional<Customer> customer = customerRepository.findById(cartItemUpdateDto.getCustomerId());
        Cart cart = cartRepository.findByCustomer(customer.get().getId());
        Optional<Product> product = productRepository.findById(cartItemUpdateDto.getProductId());

        CartItem cartItem = CartItem.builder()
                .id(cartItemUpdateDto.getCartItemId())
                .cart(cart)
                .count(cartItemUpdateDto.getCount())
                .product(product.get())
                .build();

        CartItem save = cartItemRepository.save(cartItem);

        return save;
    }

    //장바구니 삭제
    //cart item id
    @Transactional
    public void cartDelete(Long id){
        Optional<CartItem> cartItem = cartItemRepository.findById(id);

        cartItemRepository.delete(cartItem.get());
    }
}
