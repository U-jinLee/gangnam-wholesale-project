package com.gangnam.wholesale.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Table(name = "product_prices")
@Entity
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal price;

    private Long customerTierId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductPrice(BigDecimal price, Long customerTierId) {
        this.price = price;
        this.customerTierId = customerTierId;
    }

    public void setProduct(Product product) {
        this.product = product;

        if(!product.getProductPrices().contains(this)) product.getProductPrices().add(this);
    }
}