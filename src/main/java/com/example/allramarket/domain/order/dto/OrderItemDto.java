package com.example.allramarket.domain.order.dto;

import com.example.allramarket.domain.order.entity.Order;
import com.example.allramarket.domain.order.entity.OrderItem;
import com.example.allramarket.domain.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private String productName;
    private Long orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        productName = orderItem.getProduct().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemAddDto {
        private Long productId;
        private Long orderPrice;
        private int count;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemResDto {
        private Long productId;
        private Long orderPrice;
        private int count;

        public OrderItemResDto(OrderItem orderItem) {
            productId = orderItem.getProduct().getId();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
