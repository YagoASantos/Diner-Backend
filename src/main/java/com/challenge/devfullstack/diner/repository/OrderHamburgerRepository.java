package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.order.OrderHamburger;
import com.challenge.devfullstack.diner.model.order.OrderHamburgerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHamburgerRepository extends JpaRepository<OrderHamburger, OrderHamburgerId> {
}
