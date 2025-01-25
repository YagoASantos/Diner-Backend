package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.model.Ingredient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HamburgerServiceTest {

    @Test
    void shouldSetIngredientsCorrectly() {
        Ingredient ingredient = new Ingredient(null, "Testing", new BigDecimal("5.00"), true, null, null);
        List<Ingredient> ingredients = Arrays.asList(ingredient);
        Hamburger hamburger = new Hamburger();
        hamburger.setIngredients(ingredients);
        assertTrue(hamburger.getIngredients().contains(ingredient));
    }
}