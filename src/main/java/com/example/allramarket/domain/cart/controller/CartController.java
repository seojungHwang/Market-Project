package com.example.allramarket.domain.cart.controller;

import com.example.allramarket.domain.cart.dto.CartItemDto;
import com.example.allramarket.domain.cart.entity.Cart;
import com.example.allramarket.domain.cart.service.CartService;
import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    //장바구니 조회
    @GetMapping("/list/{id}")
    public CommonResponse cartList(@PathVariable("id") Long id) {
        List<CartItemDto> carts = cartService.cartList(id);

        return CommonResponse.success(carts);
    }

    //장바구니 상품 추가
    @PostMapping("/add")
    public CommonResponse cartAdd() {

        return CommonResponse.success("");
    }

    //장바구니 상품 수정
    @PutMapping("/update")
    public CommonResponse cartUpdate() {

        return CommonResponse.success("");
    }


    //장바구니 상품 삭제
    @DeleteMapping("/delete/{id}")
    public CommonResponse cartDelete(@PathVariable("id") Long id) {


        return CommonResponse.success("");
    }
}
