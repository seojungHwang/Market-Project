package com.example.allramarket.domain.order.service;

import com.example.allramarket.domain.customer.repository.CustomerRepository;
import com.example.allramarket.domain.order.repository.OrderItemRepository;
import com.example.allramarket.domain.order.repository.OrderRepository;
import com.example.allramarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

}
