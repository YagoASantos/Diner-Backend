package com.challenge.devfullstack.diner.service;

import com.challenge.devfullstack.diner.model.*;
import com.challenge.devfullstack.diner.model.drink.Drink;
import com.challenge.devfullstack.diner.model.order.OrderDrink;
import com.challenge.devfullstack.diner.model.order.Order;
import com.challenge.devfullstack.diner.model.order.OrderHamburger;
import com.challenge.devfullstack.diner.repository.OrderRepository;
import com.challenge.devfullstack.diner.util.builder.OrderBuilder;
import com.challenge.devfullstack.diner.util.dto.ObservationDto;
import com.challenge.devfullstack.diner.util.dto.order.ItemAndQuantityDto;
import com.challenge.devfullstack.diner.util.dto.order.OrderDto;
import com.challenge.devfullstack.diner.util.dto.order.PostOrderDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private HamburgerService hamburgerService;

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ClientService clientService;

    public Order create(PostOrderDto dto) {
        Order order = new OrderBuilder(dto).setHamburgers(convertHamburgersAndQuantitiesDtoToOrderHamburgers(dto.hamburgers()))
                        .setDrinks(convertHamburgersAndQuantitiesDtoToOrderDrinks(dto.drinks()))
                        .setObservations(createObservations(dto.observations()))
                        .setClient(clientService.findById(dto.clientId()))
                        .build();
        return repository.save(order);
    }

    private List<OrderHamburger> convertHamburgersAndQuantitiesDtoToOrderHamburgers(List<ItemAndQuantityDto> dto) {
        return dto.stream()
                .map(this::createNewOrderHamburger)
                .toList();
    }

    private OrderHamburger createNewOrderHamburger(ItemAndQuantityDto dto) {
        return new OrderHamburger(hamburgerService.findById(dto.itemId()), dto.quantity());
    }

    private List<OrderDrink> convertHamburgersAndQuantitiesDtoToOrderDrinks(List<ItemAndQuantityDto> dto) {
        return dto.stream()
                .map(this::createNewOrderDrink)
                .toList();
    }

    private OrderDrink createNewOrderDrink(ItemAndQuantityDto dto) {
        return new OrderDrink(drinkService.findById(dto.itemId()), dto.quantity());
    }

    private List<Observation> createObservations(List<ObservationDto> dto) {
        return dto.stream()
                .map(observation -> new Observation(observation.message()))
                .toList();
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public BigDecimal calculateTotalPrice(Order order) {
        BigDecimal totalHamburgersPrice = calculateHamburgersPrice(order.getHamburgers());
        BigDecimal totalDrinksPrice = calculateDrinksPrice(order.getDrinks());
        return totalHamburgersPrice.add(totalDrinksPrice);
    }

    private BigDecimal calculateHamburgersPrice(List<OrderHamburger> hamburgers) {
        return hamburgers.stream()
                .map(orderHamburger -> orderHamburger.getHamburger().getPrice().multiply(new BigDecimal(orderHamburger.getQuantityHamburgers())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
    }

    private BigDecimal calculateDrinksPrice(List<OrderDrink> drinks) {
        return drinks.stream()
                .map(orderDrink -> orderDrink.getDrink().getUnityPrice().multiply(new BigDecimal(orderDrink.getQuantityDrinks())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
    }

    public Page<OrderDto> findAll(Pageable page) {
        return repository.findAllByCanceledAtNull(page).map(order -> new OrderDto(order, calculateTotalPrice(order)));
    }

    public void delete(Order order) {
        repository.delete(order);
    }

    public List<OrderDto> findByDescription(String description) {
        return repository.findAllByDescriptionContainingIgnoreCase(description).stream()
                .map(order -> new OrderDto(order, calculateTotalPrice(order)))
                .toList();
    }
}
