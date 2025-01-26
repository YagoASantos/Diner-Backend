package com.challenge.devfullstack.diner.model.order;

import com.challenge.devfullstack.diner.model.drink.Drink;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders_drinks")
@NoArgsConstructor
@Getter
public class OrderDrink {

    @EmbeddedId
    private OrderDrinkId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("drinkId")
    @JoinColumn(name = "id_drink")
    private Drink drink;

    @Column(name = "quantity_drinks")
    private Integer quantityDrinks;

    @PrePersist
    private void setDefaults() {
        if (this.quantityDrinks == null) {
            this.quantityDrinks = 0;
        }
    }

    public OrderDrink(Drink drink, Integer quantityDrinks) {
        this.drink = drink;
        this.id = new OrderDrinkId();
        this.id.setDrinkId(drink.getId());
        this.quantityDrinks = quantityDrinks;
    }

    public void setOrder(Order order) {
        this.order = order;
        id.setOrderId(order.getId());
    }
}
