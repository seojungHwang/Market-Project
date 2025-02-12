package com.example.allramarket.domain.cart.dto;

import com.example.allramarket.domain.cart.entity.Cart;
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
    private Cart cart;
    private Product product;
}
