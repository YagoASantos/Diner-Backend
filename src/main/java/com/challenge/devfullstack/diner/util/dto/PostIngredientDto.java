package com.challenge.devfullstack.diner.util.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PostIngredientDto(
        @NotBlank(message = "Preencha o campo descrição.")
        @Size(min = 3, message = "Quant. mínima de caracteres não atingido(3).")
        String description,
        @NotNull
        BigDecimal price,
        @NotNull
        Boolean isAdditional
) {
}
