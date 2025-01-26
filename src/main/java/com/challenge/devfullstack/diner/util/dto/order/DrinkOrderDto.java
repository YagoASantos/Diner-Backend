package com.challenge.devfullstack.diner.util.dto.order;

import com.challenge.devfullstack.diner.model.drink.Drink;

import java.math.BigDecimal;

public record DrinkOrderDto(
        Long code,
        String description,
        BigDecimal price,
        Boolean haveSugar,
        Integer quantityOfDrinks
        ) {
    public DrinkOrderDto(Drink drink, Integer quantityOfDrinks) {
        this(drink.getId(), drink.getDescription(), drink.getUnityPrice(), drink.haveSugar(), quantityOfDrinks);
    }
}
