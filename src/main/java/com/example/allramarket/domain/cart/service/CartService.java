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

import static java.lang.Float.isNaN;
import static java.util.stream.Collectors.toList;

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

       cartRepository.save(cart);
    }

    //장바구니 조회
    //customer id
    public CartItemDto.CartListDto cartList(Long id){
        CartItemDto.CartListDto cartListDto = new CartItemDto.CartListDto();
        Cart cart = Optional.ofNullable(cartRepository.findByCustomer(id))
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for customer ID: " + id));
        List<CartItem> cartItem = Optional.ofNullable(cartItemRepository.findCartItemByCartAndId(cart.getId()))
                .orElse(Collections.emptyList());

        List<CartItemDto> cartItemDto = cartItem.stream().map(CartItemDto::new).collect(Collectors.toList());
        long totalPrice = (long) cartItemDto.stream().map(m -> m.getPrice()).collect(toList()).stream().mapToDouble(Long::longValue).sum();

        cartListDto.setCartItemList(cartItemDto);
        cartListDto.setAmount(totalPrice);

        return cartListDto;
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
    public CartItemDto.CartListDto cartDelete(CartItemDto.CartItemDeleteDto cartItemDeleteDto){
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemDeleteDto.getCartItemId());
        cartItemRepository.delete(cartItem.get());

        CartItemDto.CartListDto cartList = cartList(cartItemDeleteDto.getCustomerId());
        return cartList;
    }

    @Transactional
    public CartItemDto.CartListDto cartAllDelete(Long id){
        Cart cart = cartRepository.findByCustomer(id);
        List<CartItem> cartItem = cartItemRepository.findCartItemByCartAndId(cart.getId());
        cartItemRepository.deleteAll(cartItem);
        CartItemDto.CartListDto cartListDto = cartList(id);

        return cartListDto;
    }

}
