package com.example.allramarket.domain.cart.dto;

import com.example.allramarket.domain.cart.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private long id;
    private int count;
    private Long price;
    private long productId;
    private String productName;

    public CartItemDto(CartItem cartItem) {
        this.id = cartItem.getId();
        this.count = cartItem.getCount();
        this.price = cartItem.getCount() * cartItem.getProduct().getPrice();
        this.productId = cartItem.getProduct().getId();
        this.productName = cartItem.getProduct().getName();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemAddDto {
        private Long customerId;
        private int count;
        private Long productId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemUpdateDto {
        private Long cartItemId;
        private Long customerId;
        private int count;
        private Long productId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemDeleteDto {
        private Long cartItemId;
        private Long customerId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartListDto {
        private List<CartItemDto> cartItemList;
        private Long amount;
    }
}
