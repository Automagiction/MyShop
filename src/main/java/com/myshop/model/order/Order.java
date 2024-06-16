package com.myshop.model.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String address;
    @Column
    private String postCode;
    @Column
    private String city;
    @Column
    private LocalDateTime created;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderItem> orderItems;
}
