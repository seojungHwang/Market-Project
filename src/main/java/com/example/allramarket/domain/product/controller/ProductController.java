package com.example.allramarket.domain.product.controller;

import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.domain.product.entity.Product;
import com.example.allramarket.domain.product.service.ProductService;
import com.example.allramarket.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 목록 조회", description = "상품 목록을 조회합니다.", tags = {"Product"})
    @GetMapping("/list")
    public CommonResponse productList() {
       List<ProductDto> productList = productService.productList();

        return CommonResponse.success(productList);
    }

    @Operation(summary = "상품 등록", description = "상품을 등록합니다.", tags = {"Product"})
    @PostMapping("/add")
    public CommonResponse productAdd(@RequestBody ProductDto.ProductAddDto productAddDto) {
       Product product = productService.productAdd(productAddDto);

       return CommonResponse.success(product);
    }

    @Operation(summary = "상품 수정", description = "상품을 수정합니다.", tags = {"Product"})
    @PostMapping("/update")
    public CommonResponse productUpdate(@RequestBody ProductDto.ProductUpdateDto productUpdateDto) {
        Product product = productService.productUpdate(productUpdateDto);

        return CommonResponse.success(product);
    }
}
