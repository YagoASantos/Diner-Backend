package com.challenge.devfullstack.diner.util.dto;

import com.challenge.devfullstack.diner.model.Drink;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DrinkDto(
        Long code,
        @Size(min = 3, max = 50)
        String description,
        BigDecimal price,
        Boolean haveSugar) {
    public DrinkDto(Drink drink) {
        this(drink.getId(), drink.getDescription(), drink.getUnityPrice(), drink.haveSugar());
    }
}
