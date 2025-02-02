package com.challenge.devfullstack.diner.util.dto.hamburger;

import com.challenge.devfullstack.diner.util.dto.ErrorFieldsMessages;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record PatchHamburgerDto(
        @Size(min = 3, message = ErrorFieldsMessages.MIN_SIZE)
        String description,
        BigDecimal price,
        List<Long> ingredients
) {
}
