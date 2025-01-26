package com.challenge.devfullstack.diner.model.order;

import com.challenge.devfullstack.diner.model.Client;
import com.challenge.devfullstack.diner.model.Observation;
import com.challenge.devfullstack.diner.util.dto.order.PostOrderDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderHamburger> hamburgers;
    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDrink> drinks;
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    private List<Observation> observations;
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "deleted_at")
    private LocalDateTime canceledAt;

    public Order(PostOrderDto dto) {
        this.description = dto.description();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime orderDate = LocalDateTime.parse(dto.orderDate(), formatter);
        if (orderDate.isBefore(LocalDateTime.now())) {
            this.orderDate = orderDate;
        } else {
            throw new IllegalArgumentException("Data do pedido inválida.");
        }
    }

    public void cancel() {
        this.canceledAt = LocalDateTime.now();
    }

}
