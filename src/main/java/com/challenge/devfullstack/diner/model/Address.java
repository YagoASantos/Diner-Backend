package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.PostAddressDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "number")
    private Integer number;
    @Column(name = "complement")
    private String complement;

    public Address(PostAddressDto dto) {
        this.cep = dto.cep();
        this.street = dto.street();
        this.number = dto.number();
        this.complement = dto.complement();
    }
}
