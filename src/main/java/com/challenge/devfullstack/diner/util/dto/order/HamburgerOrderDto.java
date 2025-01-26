package com.challenge.devfullstack.diner.util.dto.order;

import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.util.dto.ingredient.IngredientDto;

import java.math.BigDecimal;
import java.util.List;

public record HamburgerOrderDto(
        Long code,
        String description,
        BigDecimal price,
        Integer quantityOfHamburgers,
        List<IngredientDto> ingredients
) {
    public HamburgerOrderDto(Hamburger hamburger, Integer quantityOfHamburgers) {
        this(hamburger.getId(), hamburger.getDescription(), hamburger.getPrice(), quantityOfHamburgers, hamburger.getIngredients().stream()
                .map(IngredientDto::new).toList());
    }
}
