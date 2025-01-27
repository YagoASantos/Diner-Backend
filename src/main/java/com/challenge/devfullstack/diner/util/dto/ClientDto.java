package com.challenge.devfullstack.diner.util.dto;


import com.challenge.devfullstack.diner.model.Client;

public record ClientDto(
        Long id,
        String name,
        AddressDto address,
        String phone
) {
    public ClientDto(Client client) {
        this(client.getId(), client.getName(), new AddressDto(client.getAddress()), client.getPhone());
    }
}
