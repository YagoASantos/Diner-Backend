package com.challenge.devfullstack.diner.util.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ItemAndQuantityDto(
        Long code,
        Integer quantity
) {
}
