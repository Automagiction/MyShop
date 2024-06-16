package com.myshop.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @Column
    private Long orderId;
    @Column
    private Long itemId;
    @Column
    private int amount;
    @Column
    private BigDecimal sum_price;

    public OrderItem(Long orderId, Long itemId, int amount, BigDecimal sum_price) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
        this.sum_price = sum_price;
    }
}
