package com.challenge.devfullstack.diner.util.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PostClientDto(
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "nome.")
        @Size(min = 3, message = ErrorFieldsMessages.MIN_SIZE)
        String name,
        @NotNull(message = "Preencha o endereço")
        @Valid
        PostAddressDto address,
        @NotBlank(message = ErrorFieldsMessages.FIELD_BLANK + "telefone.")
        @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
        String phone
) {
}
