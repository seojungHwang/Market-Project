package com.example.allramarket.domain.order.controller;

import com.example.allramarket.domain.order.service.OrderService;
import com.example.allramarket.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;


    //주문내역
    @GetMapping("/list")
    public CommonResponse orderList() {

        return CommonResponse.success("");
    }


    //결제요청
    @PostMapping("/")
    public CommonResponse order() {

        return CommonResponse.success("");
    }

}
