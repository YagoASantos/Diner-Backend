package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.ingredient.IngredientDto;
import com.challenge.devfullstack.diner.util.dto.ingredient.PostIngredientDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "unity_price", nullable = false)
    private BigDecimal unityPrice;
    @Column(name = "is_additional", nullable = false)
    private Boolean isAdditional;
    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private List<Hamburger> hamburgers;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Ingredient(PostIngredientDto dto) {
        this.description = dto.description();
        this.unityPrice = dto.price();
        this.isAdditional = dto.isAdditional();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void setChanges(IngredientDto dto) {
        if (dto.description() != null) this.description = dto.description();
        if (dto.price() != null) this.unityPrice = dto.price();
        if (dto.isAdditional() != null) this.isAdditional = dto.isAdditional();
    }

    public void addHamburger(Hamburger hamburger) {
        this.hamburgers.add(hamburger);
    }
}
