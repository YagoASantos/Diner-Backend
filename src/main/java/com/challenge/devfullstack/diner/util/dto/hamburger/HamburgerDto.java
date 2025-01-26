package com.challenge.devfullstack.diner.util.dto.hamburger;

import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.util.dto.ingredient.IngredientDto;

import java.math.BigDecimal;
import java.util.List;

public record HamburgerDto(
        Long code,
        String description,
        BigDecimal price,
        List<IngredientDto> ingredients
) {
    public HamburgerDto(Hamburger hamburger) {
        this(hamburger.getId(), hamburger.getDescription(), hamburger.getPrice(), hamburger.getIngredients().stream()
                .map(IngredientDto::new).toList());
    }
}
