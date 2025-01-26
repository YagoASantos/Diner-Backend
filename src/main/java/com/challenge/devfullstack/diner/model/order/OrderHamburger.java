package com.challenge.devfullstack.diner.model.order;

import com.challenge.devfullstack.diner.model.Hamburger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders_hamburguers")
@NoArgsConstructor
@Getter
public class OrderHamburger {

    @EmbeddedId
    private OrderHamburgerId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("hamburgerId")
    @JoinColumn(name = "id_hamburger")
    private Hamburger hamburger;

    @Column(name = "quantity_hamburgers", nullable = false)
    private Integer quantityHamburgers;

    @PrePersist
    private void setDefaults() {
        if (this.quantityHamburgers == null) {
            this.quantityHamburgers = 0;
        }
    }

    public OrderHamburger(Hamburger hamburger, Integer quantityHamburgers) {
        this.hamburger = hamburger;
        this.quantityHamburgers = quantityHamburgers;
        this.id = new OrderHamburgerId();
        id.setHamburgerId(hamburger.getId());
    }

    public void setOrder(Order order) {
        this.order = order;
        id.setOrderId(order.getId());
    }
}
