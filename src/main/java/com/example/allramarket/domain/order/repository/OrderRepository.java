package com.example.allramarket.domain.order.repository;

import com.example.allramarket.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o left join fetch o.customer c where c.id = :id ")
    List<Order> findByCustomer(@Param("id") Long id);

}
