package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
