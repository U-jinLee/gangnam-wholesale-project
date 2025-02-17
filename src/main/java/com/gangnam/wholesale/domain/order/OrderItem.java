package com.gangnam.wholesale.domain.order;

import jakarta.persistence.*;
import lombok.Builder;
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
    private Long id;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subTotalPrice;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderItem(Long productId, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotalPrice = calculateSubTotalPrice(unitPrice, quantity);
    }

    private BigDecimal calculateSubTotalPrice(BigDecimal unitPrice, Integer quantity) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void setOrder(Order order) {
        this.order = order;

        if(!order.getOrderItems().contains(this)) order.getOrderItems().add(this);
    }

}