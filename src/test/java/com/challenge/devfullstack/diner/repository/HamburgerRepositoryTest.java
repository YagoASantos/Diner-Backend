package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.model.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class HamburgerRepositoryTest {

    @Autowired
    private HamburgerRepository repository;

    @Test
    void shouldSaveBurgerCorrectlyAndReturnById() {
        Ingredient ingredient = new Ingredient(null, "Testing", new BigDecimal("5.00"), true, null, null);
        List<Ingredient> ingredients = Arrays.asList(ingredient);
        Hamburger hamburger = new Hamburger(null, "description", new BigDecimal(10.00), null, null, null);
        hamburger.setIngredients(ingredients);
        hamburger = repository.save(hamburger);
        assertTrue(repository.findById(hamburger.getId()).get().getIngredients().contains(ingredient));
    }
}