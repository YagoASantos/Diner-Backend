package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByDeletedAtNullAndNameContainingIgnoreCase(String name);

    Page<Client> findAllByDeletedAtNull(Pageable page);
}
