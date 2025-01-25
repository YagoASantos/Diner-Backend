package com.challenge.devfullstack.diner.util.dto;

import com.challenge.devfullstack.diner.model.Order;

import java.util.List;

public record OrderDto(
        Long id,
        String description,
        List<HamburgerDto> hamburgers,
        List<DrinkDto> drinks,
        List<ObservationDto> observations
) {
    public OrderDto(Order order) {
        this(order.getId(), order.getDescription(), order.getHamburgers().stream()
                .map(HamburgerDto::new).toList(), order.getDrinks().stream()
                .map(DrinkDto::new).toList(), order.getObservations().stream()
                .map(ObservationDto::new).toList());
    }
}
