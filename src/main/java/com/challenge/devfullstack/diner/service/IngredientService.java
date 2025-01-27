package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.Ingredient;
import com.challenge.devfullstack.diner.repository.IngredientRepository;
import com.challenge.devfullstack.diner.util.dto.ingredient.IngredientDto;
import com.challenge.devfullstack.diner.util.dto.ingredient.PostIngredientDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public Ingredient create(PostIngredientDto dto) {
        return repository.save(new Ingredient(dto));
    }

    public Ingredient findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<IngredientDto> findByDescription(String description, Pageable page) {
        return repository.findAllByDeletedAtNullAndDescriptionContainingIgnoreCase(description, page).map(IngredientDto::new);
    }

    public Page<IngredientDto> findAll(Pageable page) {
        return repository.findAllByDeletedAtNull(page).map(IngredientDto::new);
    }

    public Ingredient update(Long id, IngredientDto dto) {
        Ingredient ingredient = findById(id);
        ingredient.setChanges(dto);
        return repository.save(ingredient);
    }

    public void delete(Long id) {
        Ingredient deletedIngredient = findById(id);
        if (deletedIngredient.getDeletedAt() == null) {
            deletedIngredient.delete();
            repository.save(deletedIngredient);
        }
    }
}
