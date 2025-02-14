package com.gangnam.wholesale.domain.product.repository;

import java.util.Optional;

import com.gangnam.wholesale.domain.product.Supplier;

public interface SupplierRepository {
	Optional<Supplier> findById(Long id);
}