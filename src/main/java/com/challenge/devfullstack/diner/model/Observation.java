package com.challenge.devfullstack.diner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @Column(name = "observation", nullable = false)
    private String observation;

    public Observation(String observation, Order order) {
        this.observation = observation;
        this.order = order;
    }
}
