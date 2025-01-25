package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.PatchHamburgerDto;
import com.challenge.devfullstack.diner.util.dto.PostHamburgerDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "hamburguers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Hamburger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "unity_price", nullable = false)
    private BigDecimal price;
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "hamburguers_ingredients",
            joinColumns = @JoinColumn(name = "id_hamburguer"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient"))
    private List<Ingredient> ingredients;
    @ManyToMany(mappedBy = "hamburgers", fetch = FetchType.LAZY)
    private List<Order> orders;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Hamburger(PostHamburgerDto dto) {
        this.description = dto.description();
        this.price = dto.price();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void setChanges(PatchHamburgerDto dto) {
        Optional.ofNullable(dto.description()).ifPresent(description -> this.description = description);
        Optional.ofNullable(dto.price()).ifPresent(price -> this.price = price);
    }
}
