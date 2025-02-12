package com.example.allramarket.domain.customer.service;

import com.example.allramarket.domain.customer.dto.CustomerDto;
import com.example.allramarket.domain.customer.entity.Customer;
import com.example.allramarket.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer register(CustomerDto customerDto){
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        Customer save = customerRepository.save(customer);

        return save;
    }

}
