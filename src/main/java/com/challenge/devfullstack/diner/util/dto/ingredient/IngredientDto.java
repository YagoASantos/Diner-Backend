package com.challenge.devfullstack.diner.util.dto.ingredient;

import com.challenge.devfullstack.diner.model.Ingredient;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record IngredientDto(
        Long code,
        @Size(min = 3, max = 50)
        String description,
        BigDecimal price,
        Boolean isAdditional
) {
    public IngredientDto(Ingredient ingredient) {
        this(ingredient.getId(), ingredient.getDescription(), ingredient.getUnityPrice(), ingredient.getIsAdditional());
    }
}

