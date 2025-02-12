package com.example.allramarket.domain.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
