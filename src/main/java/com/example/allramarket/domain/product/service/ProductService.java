package com.example.allramarket.domain.product.service;

import com.example.allramarket.domain.cart.dto.CartItemDto;
import com.example.allramarket.domain.order.dto.OrderItemDto;
import com.example.allramarket.domain.product.dto.ProductDto;
import com.example.allramarket.domain.product.entity.Product;
import com.example.allramarket.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public Product productAdd(ProductDto.ProductAddDto productAddDto){
        Product product = Product.builder()
                .name(productAddDto.getName())
                .description(productAddDto.getDescription())
                .price(productAddDto.getPrice())
                .stock(productAddDto.getStock())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

       Product save = productRepository.save(product);
       return save;
    }

    @Transactional
    public Product productUpdate(ProductDto.ProductUpdateDto productUpdateDto){
       Optional<Product> productId = productRepository.findById(productUpdateDto.getId());

        Product product = Product.builder()
                .id(productId.get().getId())
                .name(productUpdateDto.getName())
                .description(productUpdateDto.getDescription())
                .price(productUpdateDto.getPrice())
                .stock(productUpdateDto.getStock())
                .created_at(productId.get().getCreated_at())
                .updated_at(LocalDateTime.now())
                .build();

        Product save = productRepository.save(product);
        return save;
    }

    @Transactional
    public void productStockUpdate(List<CartItemDto> list){
        List<Product> productList = new ArrayList<>();

        for (CartItemDto cartItemDto : list) {
            Optional<Product> productId = productRepository.findById(cartItemDto.getProductId());
            int restStock = productId.get().getStock() - cartItemDto.getCount();

            Product product = Product.builder()
                    .id(productId.get().getId())
                    .name(productId.get().getName())
                    .description(productId.get().getDescription())
                    .price(productId.get().getPrice())
                    .stock(restStock)
                    .created_at(productId.get().getCreated_at())
                    .updated_at(LocalDateTime.now())
                    .build();
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    //재고 체크
    public int stockCheck(Long id, int count){
        Optional<Product> product = productRepository.findById(id);

        if (product.get().getStock() < 0) {
            return 0;
        }else if (product.get().getStock() < count){
            return - product.get().getStock();
        }

        return product.get().getStock();
    }
}
