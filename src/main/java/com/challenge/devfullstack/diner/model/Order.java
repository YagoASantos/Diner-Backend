package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.PostOrderDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_hamburguers",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_hamburger"))
    private List<Hamburger> hamburgers;
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders_drinks",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_drink"))
    private List<Drink> drinks;
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    private List<Observation> observations;
    @Column(name = "deleted_at")
    private LocalDateTime canceledAt;

    public Order(PostOrderDto dto) {
        this.description = dto.description();
    }

    public void cancel() {
        this.canceledAt = LocalDateTime.now();
    }

}
