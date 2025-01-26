package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.Ingredient;
import com.challenge.devfullstack.diner.service.IngredientService;
import com.challenge.devfullstack.diner.util.dto.ingredient.IngredientDto;
import com.challenge.devfullstack.diner.util.dto.ingredient.PostIngredientDto;
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
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostIngredientDto dto, UriComponentsBuilder uriBuilder) {
        Ingredient createdIngredient = service.create(dto);
        URI uri = uriBuilder.path("/ingredients/{id}").buildAndExpand(createdIngredient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<IngredientDto>> findAll(@PageableDefault(sort = "description")Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new IngredientDto(service.findById(id)));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<IngredientDto>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(service.findByDescription(description));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<IngredientDto> update(@PathVariable Long id, @RequestBody @Valid IngredientDto dto) {
        return ResponseEntity.ok(new IngredientDto(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
