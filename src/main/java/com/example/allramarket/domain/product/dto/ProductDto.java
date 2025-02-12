package com.example.allramarket.domain.product.dto;

import com.example.allramarket.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Long price;
    private int stock;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public ProductDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.created_at = product.getCreated_at();
        this.updated_at = product.getUpdated_at();
    }
}
