package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.Client;
import com.challenge.devfullstack.diner.repository.ClientRepository;
import com.challenge.devfullstack.diner.util.dto.ClientDto;
import com.challenge.devfullstack.diner.util.dto.PostClientDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client create(PostClientDto dto) {
        return repository.save(new Client(dto));
    }

    public Client findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<ClientDto> findByName(String name) {
        return repository.findAllByDeletedAtNullAndNameContainingIgnoreCase(name).stream()
                .map(ClientDto::new)
                .toList();
    }

    public void delete(Long id) {
        Client client = findById(id);
        client.delete();
    }

    public Page<ClientDto> findAll(Pageable page) {
        return repository.findAllByDeletedAtNull(page).map(ClientDto::new);
    }
}
