package com.example.allramarket.domain.order.controller;

import com.example.allramarket.domain.order.dto.OrderDto;
import com.example.allramarket.domain.order.dto.OrderItemDto;
import com.example.allramarket.domain.order.entity.Order;
import com.example.allramarket.domain.order.entity.OrderItem;
import com.example.allramarket.domain.order.service.OrderService;
import com.example.allramarket.domain.product.service.ProductService;
import com.example.allramarket.global.response.CommonResponse;
import com.example.allramarket.global.response.StatusCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    //주문내역
    @Operation(summary = "주문내역 조회", description = "주문내역을 조회합니다.", tags = {"Order"})
    @GetMapping("/list/{id}")
    public CommonResponse orderList(@PathVariable("id") @Schema(description = "customer id", example = "1") Long id) {
        List<OrderDto> orderDto = orderService.orderList(id);

        return CommonResponse.success(orderDto);
    }


    //주문요청
    @Operation(summary = "주문 요청", description = "주문 요청합니다.", tags = {"Order"})
    @PostMapping("/")
    public CommonResponse order(@RequestBody OrderDto.OrderAddDto id) throws JsonProcessingException {
        JsonNode orderItems = orderService.order(id);

        return CommonResponse.success(orderItems);
    }

}
