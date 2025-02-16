package com.example.allramarket.domain.cart.controller;

import com.example.allramarket.domain.cart.dto.CartItemDto;
import com.example.allramarket.domain.cart.entity.Cart;
import com.example.allramarket.domain.cart.entity.CartItem;
import com.example.allramarket.domain.cart.service.CartService;
import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.domain.product.service.ProductService;
import com.example.allramarket.global.response.CommonResponse;
import com.example.allramarket.global.response.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    //장바구니 조회
    @Operation(summary = "장바구니 목록 조회", description = "장바구니 목록들을 조회합니다.", tags = {"Cart"})
    @GetMapping("/list/{id}")
    public CommonResponse cartList(@PathVariable("id") @Schema(description = "customer id", example = "1")Long id) {
        CartItemDto.CartListDto carts = cartService.cartList(id);

        return CommonResponse.success(carts);
    }

    //장바구니 상품 추가
    @Operation(summary = "장바구니 추가", description = "장바구니에 상품을 추가합니다.", tags = {"Cart"})
    @PostMapping("/add")
    public CommonResponse cartAdd(@RequestBody CartItemDto.CartItemAddDto cartItemAddDto) {
        int stock = productService.stockCheck(cartItemAddDto.getProductId(), cartItemAddDto.getCount());

        if (stock == 0){
            return CommonResponse.error(StatusCode.OUT_OF_STOCK.getCode(), StatusCode.OUT_OF_STOCK.getStatus());
        }else if (stock < 0){
            return CommonResponse.error(StatusCode.OVERSTOCK.getCode(), StatusCode.OVERSTOCK.getStatus() + " 상품 수량 : " + Math.abs(stock)
            );
        }

        CartItem cartItem = cartService.cartAdd(cartItemAddDto);

        return CommonResponse.success(cartItem);
    }

    //장바구니 상품 수정
    @Operation(summary = "장바구니 수량 변경", description = "장바구니에 담긴 상품의 수량을 변경합니다.", tags = {"Cart"})
    @PutMapping("/update")
    public CommonResponse cartUpdate(@RequestBody CartItemDto.CartItemUpdateDto cartItemUpdateDto) {
        int stock = productService.stockCheck(cartItemUpdateDto.getProductId(), cartItemUpdateDto.getCount());

        if (stock == 0){
            return CommonResponse.error(StatusCode.OUT_OF_STOCK.getCode(), StatusCode.OUT_OF_STOCK.getStatus());
        }else if (stock < 0){
            return CommonResponse.error(StatusCode.OVERSTOCK.getCode(), StatusCode.OVERSTOCK.getStatus() + " 상품 수량 : " + Math.abs(stock));
        }

       CartItem cartItem = cartService.cartUpdate(cartItemUpdateDto);

        return CommonResponse.success(cartItem);
    }

    //장바구니 상품 삭제
    @Operation(summary = "장바구니 단건 삭제", description = "장바구니 단건을 삭제합니다.", tags = {"Cart"})
    @DeleteMapping("/delete")
    public CommonResponse cartDelete(@RequestBody CartItemDto.CartItemDeleteDto cartItemDeleteDto) {
        CartItemDto.CartListDto cartItemDto = cartService.cartDelete(cartItemDeleteDto);

        return CommonResponse.success(cartItemDto);
    }

    //장바구니 전체 삭제
    @Operation(summary = "장바구니 전체 삭제", description = "장바구니 전체 목록을 삭제합니다.", tags = {"Cart"})
    @DeleteMapping("/deleteAll/{id}")
    public CommonResponse cartDelete(@PathVariable("id") @Schema(description = "customer id", example = "1")Long id) {
        CartItemDto.CartListDto cartListDto = cartService.cartAllDelete(id);

        return CommonResponse.success(cartListDto);
    }
}
