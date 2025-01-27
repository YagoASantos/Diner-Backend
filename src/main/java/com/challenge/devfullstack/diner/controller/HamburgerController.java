package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.service.HamburgerService;
import com.challenge.devfullstack.diner.util.dto.hamburger.HamburgerDto;
import com.challenge.devfullstack.diner.util.dto.hamburger.PatchHamburgerDto;
import com.challenge.devfullstack.diner.util.dto.hamburger.PostHamburgerDto;
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
@RequestMapping("/hamburgers")
public class HamburgerController {

    @Autowired
    private HamburgerService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostHamburgerDto dto, UriComponentsBuilder uriBuilder) {
        Hamburger createdHamburger = service.create(dto);
        URI uri = uriBuilder.path("/hamburgers/{code}").buildAndExpand(createdHamburger.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<HamburgerDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new HamburgerDto(service.findById(id)));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<HamburgerDto>> findByDescription(@PathVariable String description) {
        return ResponseEntity.ok(service.findByDescription(description));
    }

    @GetMapping
    public ResponseEntity<Page<HamburgerDto>> findAll(@PageableDefault(sort = "description")Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @PatchMapping("/{code}")
    @Transactional
    public ResponseEntity<HamburgerDto> update(@PathVariable Long id, @RequestBody @Valid PatchHamburgerDto dto) {
        return ResponseEntity.ok(new HamburgerDto(service.update(id, dto)));
    }

    @DeleteMapping("/{code}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
