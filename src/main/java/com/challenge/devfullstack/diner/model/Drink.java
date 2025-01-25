package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.DrinkDto;
import com.challenge.devfullstack.diner.util.dto.PostDrinkDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "drinks")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", length = 100, nullable = false)
    private String description;
    @Column(name = "unity_price", nullable = false)
    private BigDecimal unityPrice;
    @Column(name = "have_sugar", nullable = false)
    private Boolean haveSugar;
    @ManyToMany(mappedBy = "drinks")
    private List<Order> orders;
    @Column(name= "deleted_at")
    private LocalDateTime deletedAt;

    public Drink(PostDrinkDto dto) {
        this.description = dto.description();
        this.unityPrice = dto.price();
        this.haveSugar = dto.haveSugar();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void setChanges(DrinkDto dto) {
        if (dto.description() != null) this.description = dto.description();
        if (dto.haveSugar() != null) this.haveSugar = dto.haveSugar();
        if (dto.price() != null) this.unityPrice = dto.price();
    }

    public Boolean haveSugar() {
        return haveSugar;
    }
}
