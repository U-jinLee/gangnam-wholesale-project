package com.gangnam.wholesale.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Table(name = "product_prices")
@Entity
public class ProductPrice {

    private Long id;

    private BigDecimal price;

    private Long customerTierId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}