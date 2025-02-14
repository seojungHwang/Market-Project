package com.example.allramarket.domain.cart.repository;

import com.example.allramarket.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "select i from CartItem i left join fetch i.cart c where i.id = :id ")
    List<CartItem> findCartItemByCartAndId(@Param("id") Long id);

}
