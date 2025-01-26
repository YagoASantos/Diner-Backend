package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "observations")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @Column(name = "observation", nullable = false)
    private String observation;

    public Observation(String observation) {
        this.observation = observation;
    }
}
