package com.example.allramarket.domain.order.service;

import com.example.allramarket.domain.cart.dto.CartItemDto;
import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.cart.repository.CartItemRepository;
import com.example.allramarket.domain.cart.service.CartService;
import com.example.allramarket.domain.customer.entity.Customer;
import com.example.allramarket.domain.customer.repository.CustomerRepository;
import com.example.allramarket.domain.order.dto.OrderDto;
import com.example.allramarket.domain.order.dto.OrderItemDto;
import com.example.allramarket.domain.order.entity.Order;
import com.example.allramarket.domain.order.entity.OrderItem;
import com.example.allramarket.domain.order.repository.OrderItemRepository;
import com.example.allramarket.domain.order.repository.OrderRepository;
import com.example.allramarket.domain.product.entity.Product;
import com.example.allramarket.domain.product.repository.ProductRepository;
import com.example.allramarket.domain.product.service.ProductService;
import com.example.allramarket.global.exception.CustomException;
import com.example.allramarket.global.response.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final CartService cartService;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${payment.url}")
    private String paymentUrl;


    //주문내역 조회
    public List<OrderDto> orderList(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        List<Order> orders = Optional.ofNullable(orderRepository.findByCustomer(customer.get().getId())).orElse(Collections.emptyList());
        List<OrderDto> orderDto = orders.stream().map(o -> new OrderDto(o)).collect(toList());

        return orderDto;
    }

    //주문요청
    @Transactional
    public JsonNode order(OrderDto.OrderAddDto id) throws JsonProcessingException {
        Optional<Customer> customer = customerRepository.findById(id.getCustomerId());
        CartItemDto.CartListDto cartListDto = cartService.cartList(customer.get().getId());
        OrderDto.PaymentRequest payment = new OrderDto.PaymentRequest();

        Order order = Order.builder()
                .customer(customer.get())
                .amount(cartListDto.getAmount())
                .build();

        List<OrderItem> list  = cartListDto.getCartItemList().stream()
                .map(dto -> OrderItem.builder()
                        .order(order)
                        .product(Product.builder().id(dto.getProductId()).build())
                        .orderPrice(dto.getPrice())
                        .count(dto.getCount())
                        .build())
                .collect(Collectors.toList());

        Order order1 = orderRepository.save(order);
        productService.productStockUpdate(cartListDto.getCartItemList());
        orderItemRepository.saveAll(list);
        cartService.cartAllDelete(customer.get().getId());

        payment.setOrderId(order1.getId());
        payment.setAmount(cartListDto.getAmount());

        String result = payment(payment);
        JsonNode jsonNode = objectMapper.readTree(result);

        if (!jsonNode.get("status").asText().equals("SUCCESS")) {
            throw new CustomException(jsonNode);
        }
        return jsonNode;
    }

    //결제요청
    public String payment(OrderDto.PaymentRequest paymentRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<OrderDto.PaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    paymentUrl,
                    HttpMethod.POST,
                    entity,
                    String.class);

            // 결제 성공
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // 결제 실패
            return e.getResponseBodyAsString();
        }
    }
}
