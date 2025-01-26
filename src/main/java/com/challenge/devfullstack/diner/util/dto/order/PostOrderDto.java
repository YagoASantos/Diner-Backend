package com.challenge.devfullstack.diner.util.dto.order;

import java.util.List;

import com.challenge.devfullstack.diner.util.dto.ErrorFieldsMessages;
import com.challenge.devfullstack.diner.util.dto.ObservationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostOrderDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "descrição.")
        String description,
        Long clientId,
        List<ItemAndQuantityDto> hamburgers,
        List<ItemAndQuantityDto> drinks,
        List<ObservationDto> observations,
        @NotNull(message = ErrorFieldsMessages.FIELD_BLANK + "data do pedido.")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        String orderDate
) {
}
