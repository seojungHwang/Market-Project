package com.example.allramarket.domain.cart.repository;

import com.example.allramarket.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select c from Cart c left join fetch c.customer cu where c.id = :id ")
    Cart findByCustomer(Long id);


}
