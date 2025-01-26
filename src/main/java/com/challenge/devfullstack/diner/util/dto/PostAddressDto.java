package com.challenge.devfullstack.diner.util.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PostAddressDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "cep.")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "Preencha o CEP corretamente.")
        String cep,
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "rua.")
        String street,
        @NotNull(message = ErrorFieldsMessages.FIELD_BLANK + "número.")
        Integer number,
        String complement
) {
}
