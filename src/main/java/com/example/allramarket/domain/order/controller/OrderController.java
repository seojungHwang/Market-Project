package com.example.allramarket.domain.order.controller;

import com.example.allramarket.domain.order.dto.OrderDto;
import com.example.allramarket.domain.order.service.OrderService;
import com.example.allramarket.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    //주문내역
    @GetMapping("/list/{id}")
    public CommonResponse orderList(@PathVariable("id") Long id) {
        List<OrderDto> orderDto = orderService.orderList(id);

        return CommonResponse.success(orderDto);
    }


    //결제요청
    @PostMapping("/")
    public CommonResponse order() {

        return CommonResponse.success("");
    }

}
