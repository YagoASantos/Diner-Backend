package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.drink.Drink;
import com.challenge.devfullstack.diner.repository.DrinkRepository;
import com.challenge.devfullstack.diner.util.dto.drink.DrinkDto;
import com.challenge.devfullstack.diner.util.dto.drink.PostDrinkDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository repository;

    public Drink create(PostDrinkDto dto) {
        return repository.save(new Drink(dto));
    }

    public Drink findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<DrinkDto> findByDescription(String description) {
        return repository.findAllByDeletedAtNullAndDescriptionContainingIgnoreCase(description).stream()
                .map(DrinkDto::new).toList();
    }

    public Page<DrinkDto> findAll(Pageable page) {
        return repository.findAllByDeletedAtNull(page).map(DrinkDto::new);
    }

    public Drink update(Long id, DrinkDto dto) {
        Drink drink = findById(id);
        drink.setChanges(dto);
        return repository.save(drink);
    }

    public void delete(Long id) {
        Drink deletedDrink = findById(id);
        if(deletedDrink.getDeletedAt() == null) {
            deletedDrink.delete();
            repository.save(deletedDrink);
        }
    }
}
