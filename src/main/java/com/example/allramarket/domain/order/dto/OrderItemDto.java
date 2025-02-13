package com.example.allramarket.domain.order.dto;

import com.example.allramarket.domain.order.entity.OrderItem;

public class OrderItemDto {
    private String productName;
    private Long amount;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        productName = orderItem.getProduct().getName();
        amount = orderItem.getAmount();
        count = orderItem.getCount();
    }
}
