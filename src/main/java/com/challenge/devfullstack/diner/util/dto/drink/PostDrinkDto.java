package com.challenge.devfullstack.diner.util.dto.drink;

import com.challenge.devfullstack.diner.util.dto.ErrorFieldsMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PostDrinkDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "descrição.")
        @Size(min = 3, message = ErrorFieldsMessages.MIN_SIZE)
        String description,
        @NotNull(message = "Preencha o campo preço.")
        BigDecimal price,
        @NotNull(message = "Preencha o campo contém açúcar.")
        Boolean haveSugar
) {
}
