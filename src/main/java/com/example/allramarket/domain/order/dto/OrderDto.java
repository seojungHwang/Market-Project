package com.example.allramarket.domain.order.dto;

import com.example.allramarket.domain.order.entity.Order;
import com.example.allramarket.domain.order.entity.OrderItem;
import com.example.allramarket.domain.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private String name;
    private long amount;
    private LocalDateTime orderDate;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getCustomer().getName();
        amount = order.getAmount();
        orderDate = order.getCreated_at();
        orderItems = order.getOrderItems().stream().map(orderItem -> new OrderItemDto(orderItem)).collect(toList());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderAddDto{
        private Long customerId;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentRequest{
        private Long orderId;
        private Long amount;
    }


}
