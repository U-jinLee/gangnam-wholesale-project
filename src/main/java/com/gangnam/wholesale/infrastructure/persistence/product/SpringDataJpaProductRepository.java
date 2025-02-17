package com.gangnam.wholesale.infrastructure.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Product;

@Repository
public interface SpringDataJpaProductRepository extends JpaRepository<Product, Long> {
}
