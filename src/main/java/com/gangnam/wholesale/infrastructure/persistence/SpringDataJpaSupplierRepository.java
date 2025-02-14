package com.gangnam.wholesale.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Supplier;

@Repository
public interface SpringDataJpaSupplierRepository extends JpaRepository<Supplier, Long> {}