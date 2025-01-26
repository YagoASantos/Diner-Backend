package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByCanceledAtNull(Pageable page);
    List<Order> findAllByDescriptionContainingIgnoreCase(String description);
}
