package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Page<Ingredient> findAllByDeletedAtNull(Pageable page);
}
