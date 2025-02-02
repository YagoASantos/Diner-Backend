package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.drink.Drink;
import com.challenge.devfullstack.diner.service.DrinkService;
import com.challenge.devfullstack.diner.util.dto.drink.DrinkDto;
import com.challenge.devfullstack.diner.util.dto.drink.PostDrinkDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    private DrinkService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostDrinkDto dto, UriComponentsBuilder uriBuilder) {
        Drink createdDrink = service.create(dto);
        URI uri = uriBuilder.path("/drinks/{code}").buildAndExpand(createdDrink.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DrinkDto>> findAll(@PageableDefault(sort = "description")Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping("/{code}")
    public ResponseEntity<DrinkDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new DrinkDto(service.findById(id)));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<DrinkDto>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(service.findByDescription(description));
    }

    @PatchMapping("/{code}")
    @Transactional
    public ResponseEntity<DrinkDto> update(@PathVariable Long id, @RequestBody @Valid DrinkDto dto) {
        return ResponseEntity.ok(new DrinkDto(service.update(id, dto)));
    }

    @DeleteMapping("/{code}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
