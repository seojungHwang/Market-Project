package com.example.allramarket.domain.cart.dto;

import com.example.allramarket.domain.cart.entity.Cart;
import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
