package com.challenge.devfullstack.diner.util.builder;

import com.challenge.devfullstack.diner.model.Client;
import com.challenge.devfullstack.diner.model.Observation;
import com.challenge.devfullstack.diner.model.order.OrderDrink;
import com.challenge.devfullstack.diner.model.order.Order;
import com.challenge.devfullstack.diner.model.order.OrderHamburger;
import com.challenge.devfullstack.diner.util.dto.order.PostOrderDto;

import java.util.List;

public class OrderBuilder {

    private Order order;

    public OrderBuilder(PostOrderDto dto) {
        order = new Order(dto);
    }

    public OrderBuilder setClient(Client client) {
        this.order.setClient(client);
        return this;
    }

    public OrderBuilder setHamburgers(List<OrderHamburger> hamburgers) {
        hamburgers.forEach(hamburger -> hamburger.setOrder(this.order));
        this.order.setHamburgers(hamburgers);
        return this;
    }

    public OrderBuilder setDrinks(List<OrderDrink> drinks) {
        drinks.forEach(drink -> drink.setOrder(this.order));
        this.order.setDrinks(drinks);
        return this;
    }

    public OrderBuilder setObservations(List<Observation> observations) {
        observations.forEach(observation -> observation.setOrder(this.order));
        this.order.setObservations(observations);
        return this;
    }

    public Order build() {
        return order;
    }

}
