package com.challenge.devfullstack.diner.model;

import com.challenge.devfullstack.diner.util.dto.PostClientDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_address")
    private Address address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Client(PostClientDto dto) {
        this.name = dto.name();
        this.address = new Address(dto.address());
        this.phone = dto.phone();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
