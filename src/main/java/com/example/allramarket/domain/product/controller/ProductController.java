package com.example.allramarket.domain.product.controller;

import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.domain.product.entity.Product;
import com.example.allramarket.domain.product.service.ProductService;
import com.example.allramarket.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public CommonResponse recipeSave() {
       List<ProductDto> productList = productService.productList();

        return CommonResponse.success(productList);
    }

}
