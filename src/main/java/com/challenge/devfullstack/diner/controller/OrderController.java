package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.order.Order;
import com.challenge.devfullstack.diner.service.OrderService;
import com.challenge.devfullstack.diner.util.dto.order.OrderDto;
import com.challenge.devfullstack.diner.util.dto.order.PostOrderDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostOrderDto dto, UriComponentsBuilder uriBuilder) {
        Order order = service.create(dto);
        URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
        Order order = service.findById(id);
        return ResponseEntity.ok(new OrderDto(order, service.calculateTotalPrice(order)));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<OrderDto>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(service.findByDescription(description));
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto>> findAll(@PageableDefault(sort = "orderDate", direction = Sort.Direction.DESC)Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancel(@PathVariable Long id) {
        Order order = service.findById(id);
        order.cancel();
        service.delete(order);
        return ResponseEntity.ok().build();
    }
}
