package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.drink.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Page<Drink> findAllByDeletedAtNull(Pageable pageable);
    List<Drink> findAllByDeletedAtNullAndDescriptionContainingIgnoreCase(String description);
}
