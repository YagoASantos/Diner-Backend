package com.challenge.devfullstack.diner.util.dto.order;

public record ItemAndQuantityDto(
        Long itemId,
        Integer quantity
) {
}
