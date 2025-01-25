package com.challenge.devfullstack.diner.util.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;

public record PostOrderDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "descrição")
        String description,
        List<Long> hamburgers,
        List<Long> drinks,
        List<ObservationDto> observations
) {
}
