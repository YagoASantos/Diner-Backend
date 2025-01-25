package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.Order;
import com.challenge.devfullstack.diner.service.OrderService;
import com.challenge.devfullstack.diner.util.dto.OrderDto;
import com.challenge.devfullstack.diner.util.dto.PostOrderDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostOrderDto dto, UriComponentsBuilder uriBuilder) {
        Order order = service.create(dto);
        URI uri = uriBuilder.path("/api/orders").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new OrderDto(service.findById(id)));
    }
}
