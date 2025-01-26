package com.challenge.devfullstack.diner.util.dto;

import com.challenge.devfullstack.diner.model.Address;

public record AddressDto(
        String cep,
        String street,
        Integer number,
        String complement
) {
    public AddressDto(Address address) {
        this(address.getCep(), address.getStreet(), address.getNumber(), address.getComplement());
    }
}
