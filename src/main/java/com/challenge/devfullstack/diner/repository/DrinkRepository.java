package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Page<Drink> findAllByDeletedAtNull(Pageable pageable);
}
