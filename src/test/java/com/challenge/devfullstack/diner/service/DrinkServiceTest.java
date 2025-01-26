package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.drink.Drink;
import com.challenge.devfullstack.diner.repository.DrinkRepository;
import com.challenge.devfullstack.diner.util.dto.drink.DrinkDto;
import com.challenge.devfullstack.diner.util.dto.drink.PostDrinkDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DrinkServiceTest {

    @InjectMocks
    private DrinkService service;

    @Mock
    private DrinkRepository repository;

    @Captor
    private ArgumentCaptor<Drink> drinkArgumentCaptor;

    private Drink mockedDrink;

    private PostDrinkDto mockedDrinkDto;

    @BeforeEach
    void setUp() {
        mockedDrinkDto = new PostDrinkDto("Testing", new BigDecimal("10.00"), true);
        mockedDrink = new Drink(mockedDrinkDto);
    }

    @Test
    void shouldConvertDtoToEntityCorrectlyAndSave() {
        service.create(mockedDrinkDto);
        then(repository).should().save(drinkArgumentCaptor.capture());
        Drink savedDrink = drinkArgumentCaptor.getValue();
        assertEquals(mockedDrink.getDescription(), savedDrink.getDescription());
        assertEquals(mockedDrink.getUnityPrice(), savedDrink.getUnityPrice());
        assertEquals(mockedDrink.haveSugar(), savedDrink.haveSugar());
    }

    @Test
    void shouldReturnEntityNotFoundExceptionWhenDrinkIsNotFoundById() {
        assertThrows(EntityNotFoundException.class, () -> service.findById(4L));
    }

    @Test
    void shouldSetDeletedAtWhenDrinkIsDeleted() {
        given(repository.findById(mockedDrink.getId())).willReturn(Optional.ofNullable(mockedDrink));
        service.delete(mockedDrink.getId());
        then(repository).should().save(drinkArgumentCaptor.capture());
        assertNotNull(drinkArgumentCaptor.getValue().getDeletedAt());
    }

    @Test
    void shouldSetChangesCorrectly() {
        given(repository.findById(mockedDrink.getId())).willReturn(Optional.ofNullable(mockedDrink));

        DrinkDto dto = new DrinkDto(null, "Description changed", new BigDecimal("20.00"), false);
        service.update(mockedDrink.getId(), dto);
        then(repository).should().save(drinkArgumentCaptor.capture());
        Drink updatedDrink = drinkArgumentCaptor.getValue();
        assertEquals(dto.description(), updatedDrink.getDescription());
        assertEquals(dto.price(), updatedDrink.getUnityPrice());
        assertEquals(dto.haveSugar(), updatedDrink.haveSugar());
    }

}