package com.challenge.devfullstack.diner.model.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderDrinkId {

    @Column(name = "id_order")
    private Long orderId;

    @Column(name = "id_drink")
    private Long drinkId;
}
