package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.Drink;
import com.challenge.devfullstack.diner.model.Hamburger;
import com.challenge.devfullstack.diner.model.Observation;
import com.challenge.devfullstack.diner.model.Order;
import com.challenge.devfullstack.diner.repository.OrderRepository;
import com.challenge.devfullstack.diner.util.dto.ObservationDto;
import com.challenge.devfullstack.diner.util.dto.PostOrderDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private HamburgerService hamburgerService;

    @Autowired
    private DrinkService drinkService;

    public Order create(PostOrderDto dto) {
        Order order = new Order(dto);
        order.setHamburgers(findHamburgersById(dto.hamburgers()));
        order.setDrinks(findDrinksById(dto.drinks()));
        order.setObservations(createObservations(dto.observations(), order));
        return repository.save(order);
    }

    private List<Hamburger> findHamburgersById(List<Long> hamburgersId) {
        return hamburgersId.stream()
                .map(hamburgerId -> hamburgerService.findById(hamburgerId))
                .toList();
    }

    private List<Drink> findDrinksById(List<Long> drinksId) {
        return drinksId.stream()
                .map(drinkId -> drinkService.findById(drinkId))
                .toList();
    }

    private List<Observation> createObservations(List<ObservationDto> dto, Order order) {
        return dto.stream()
                .map(observation -> new Observation(observation.message(), order))
                .toList();
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
