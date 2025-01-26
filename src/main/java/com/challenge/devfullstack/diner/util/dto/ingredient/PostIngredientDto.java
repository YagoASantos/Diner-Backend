package com.challenge.devfullstack.diner.util.dto.ingredient;

import com.challenge.devfullstack.diner.util.dto.ErrorFieldsMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PostIngredientDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "descrição.")
        @Size(min = 3, message = ErrorFieldsMessages.MIN_SIZE)
        String description,
        @NotNull
        BigDecimal price,
        @NotNull
        Boolean isAdditional
) {
}
