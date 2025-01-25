package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
