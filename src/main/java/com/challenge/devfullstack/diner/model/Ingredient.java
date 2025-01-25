package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.IngredientDto;
import com.challenge.devfullstack.diner.util.dto.PostIngredientDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@Getter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", length = 100)
    private String description;
    @Column(name = "unity_price")
    private BigDecimal unityPrice;
    @Column(name = "is_additional")
    private Boolean isAdditional;
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
}
