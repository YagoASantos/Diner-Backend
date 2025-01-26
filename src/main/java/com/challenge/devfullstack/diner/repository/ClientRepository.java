package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByDeletedAtNullAndNameContainingIgnoreCase(String name);
}
