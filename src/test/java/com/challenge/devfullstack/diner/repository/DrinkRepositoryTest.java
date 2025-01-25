package com.challenge.devfullstack.diner.repository;

import com.challenge.devfullstack.diner.model.Drink;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DrinkRepositoryTest {

    @Autowired
    private DrinkRepository repository;

    private Drink mockedDrink;

    @BeforeEach
    void setUp() {
        mockedDrink = new Drink(null, "Coca-Cola lata 350ml", new BigDecimal("5.00"), true, null, null);
        mockedDrink = repository.save(mockedDrink);
    }

    @Test
    void shouldSaveDrinkCorrectlyAndReturnById() {

        assertEquals(mockedDrink, repository.findById(mockedDrink.getId()).get());
    }

    @Test
    void shouldDeleteDrinkSoftly() {
        mockedDrink.delete();
        repository.save(mockedDrink);
        assertEquals(mockedDrink.getDeletedAt(), repository.findById(mockedDrink.getId()).get().getDeletedAt());
    }
}