package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.model.Ingredient;
import com.challenge.devfullstack.diner.repository.HamburgerRepository;
import com.challenge.devfullstack.diner.util.dto.hamburger.HamburgerDto;
import com.challenge.devfullstack.diner.util.dto.hamburger.PatchHamburgerDto;
import com.challenge.devfullstack.diner.util.dto.hamburger.PostHamburgerDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HamburgerService {

    @Autowired
    private HamburgerRepository repository;

    @Autowired
    private IngredientService ingredientService;

    public Hamburger create(PostHamburgerDto dto) {
        Hamburger hamburger = new Hamburger(dto);
        List<Ingredient> ingredients = findIngredientsById(dto.ingredients());
        ingredients.forEach(ingredient -> ingredient.addHamburger(hamburger));
        hamburger.setIngredients(ingredients);
        return repository.save(hamburger);
    }

    private List<Ingredient> findIngredientsById(List<Long> ingredientsId) {
        return ingredientsId.stream()
                .map(ingredientId -> ingredientService.findById(ingredientId))
                .toList();
    }

    public Hamburger findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<HamburgerDto> findByDescription(String description) {
        return repository.findAllByDeletedAtNullAndDescriptionContainingIgnoreCase(description).stream()
                .map(HamburgerDto::new).toList();
    }

    public Page<HamburgerDto> findAll(Pageable page) {
        return repository.findAllByDeletedAtNull(page).map(HamburgerDto::new);
    }

    public Hamburger update(Long id, PatchHamburgerDto dto) {
        Hamburger hamburger = findById(id);
        if (dto.ingredients() != null && !dto.ingredients().isEmpty()) {
            hamburger.setIngredients(findIngredientsById(dto.ingredients()));
        }
        hamburger.setChanges(dto);
        return repository.save(hamburger);
    }

    public void delete(Long id) {
        Hamburger hamburger = findById(id);
        if(hamburger.getDeletedAt() == null) {
            hamburger.delete();
            repository.save(hamburger);
        }
    }
}
