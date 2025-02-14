package com.example.allramarket.domain.cart.dto;

import com.example.allramarket.domain.cart.entity.Cart;
import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private long id;
    private int count;
    private Long price;
    private String productName;

    public CartItemDto(CartItem cartItem){
        this.id = cartItem.getId();
        this.count = cartItem.getCount();
        this.price = cartItem.getCount() * cartItem.getProduct().getPrice();
        this.productName = cartItem.getProduct().getName();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemAddDto{
        private Long customerId;
        private int count;
        private Long productId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemUpdateDto{
        private Long cartItemId;
        private Long customerId;
        private int count;
        private Long productId;
    }
}
