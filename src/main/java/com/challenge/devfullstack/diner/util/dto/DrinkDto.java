package com.challenge.devfullstack.diner.util.dto;

import com.challenge.devfullstack.diner.model.Drink;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DrinkDto(
        @Size(min = 3, max = 50)
        String description,
        BigDecimal price,
        Boolean haveSugar) {
    public DrinkDto(Drink drink) {
        this(drink.getDescription(), drink.getUnityPrice(), drink.haveSugar());
    }
}
