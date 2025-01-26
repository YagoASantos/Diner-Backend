package com.challenge.devfullstack.diner.util.dto.hamburger;

import com.challenge.devfullstack.diner.util.dto.ErrorFieldsMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record PostHamburgerDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "descrição")
        @Size(min = 3, message = ErrorFieldsMessages.MIN_SIZE)
        String description,
        @NotNull(message = ErrorFieldsMessages.FIELD_BLANK + "preço.")
        BigDecimal price,
        @NotEmpty(message = ErrorFieldsMessages.FIELD_BLANK + "ingredientes")
        List<Long> ingredients
) {
}
