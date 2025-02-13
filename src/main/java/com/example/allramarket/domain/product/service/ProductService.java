package com.example.allramarket.domain.product.service;

import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.domain.product.entity.Product;
import com.example.allramarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService{

    private final ProductRepository productRepository;


    //상품 조회
    public List<ProductDto> productList(){
       List<Product> productList = productRepository.findAll();
       List<ProductDto> productDto = productList.stream().map(map -> new ProductDto(map)).collect(Collectors.toList());

       return productDto;
    }

}
