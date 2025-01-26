package com.challenge.devfullstack.diner.util.dto.order;

import com.challenge.devfullstack.diner.model.order.Order;
import com.challenge.devfullstack.diner.util.dto.ClientDto;
import com.challenge.devfullstack.diner.util.dto.ObservationDto;
import com.challenge.devfullstack.diner.util.dto.drink.DrinkDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record OrderDto(
        Long id,
        String description,
        ClientDto client,
        List<HamburgerOrderDto> hamburgers,
        List<DrinkOrderDto> drinks,
        List<ObservationDto> observations,
        LocalDateTime orderDate,
        BigDecimal totalPrice
) {
    public OrderDto(Order order, BigDecimal totalPrice) {
        this(order.getId(), order.getDescription(), new ClientDto(order.getClient()), order.getHamburgers().stream()
                .map(h -> new HamburgerOrderDto(h.getHamburger(), h.getQuantityHamburgers())).toList(), order.getDrinks().stream()
                .map(d -> new DrinkOrderDto(d.getDrink(), d.getQuantityDrinks())).toList(), order.getObservations().stream()
                .map(ObservationDto::new).toList(), order.getOrderDate(), totalPrice);
    }

}