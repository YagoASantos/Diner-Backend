package com.challenge.devfullstack.diner.controller;

import com.challenge.devfullstack.diner.model.Client;
import com.challenge.devfullstack.diner.service.ClientService;
import com.challenge.devfullstack.diner.util.dto.ClientDto;
import com.challenge.devfullstack.diner.util.dto.PostClientDto;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostClientDto dto, UriComponentsBuilder uriBuilder) {
        Client client = service.create(dto);
        URI uri = uriBuilder.path("/clients/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(@PageableDefault(sort = "name")Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ClientDto(service.findById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ClientDto>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }
}
