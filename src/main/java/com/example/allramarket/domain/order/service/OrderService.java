package com.example.allramarket.domain.order.service;

import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.customer.repository.CustomerRepository;
import com.example.allramarket.domain.order.dto.OrderDto;
import com.example.allramarket.domain.order.entity.Order;
import com.example.allramarket.domain.order.entity.OrderItem;
import com.example.allramarket.domain.order.repository.OrderItemRepository;
import com.example.allramarket.domain.order.repository.OrderRepository;
import com.example.allramarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    //주문내역 조회
    public List<OrderDto> orderList(Long id){
        List<Order> orders = Optional.ofNullable(orderRepository.findAll()).orElse(Collections.emptyList());;
        List<OrderDto> orderDto = orders.stream().map(o -> new OrderDto(o)).collect(toList());

        return orderDto;
    }

}
