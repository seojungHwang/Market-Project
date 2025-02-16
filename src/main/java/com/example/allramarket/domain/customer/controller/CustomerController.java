package com.example.allramarket.domain.customer.controller;

import com.example.allramarket.domain.customer.dto.CustomerDto;
import com.example.allramarket.domain.customer.entity.Customer;
import com.example.allramarket.domain.customer.service.CustomerService;
import com.example.allramarket.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    //회원가입
    @Operation(summary = "회원가입", description = "고객이 회원가입합니다.", tags = {"Customer"})
    @PostMapping("/register")
    public CommonResponse customerRegister(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.register(customerDto);

        return CommonResponse.success(customer);
    }
}
