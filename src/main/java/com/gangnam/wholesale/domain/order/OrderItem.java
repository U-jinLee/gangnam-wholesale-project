package com.gangnam.wholesale.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Table(name = "order_items")
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subTotalPrice;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}