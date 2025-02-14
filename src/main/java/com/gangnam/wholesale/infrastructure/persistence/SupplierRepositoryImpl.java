package com.gangnam.wholesale.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Supplier;
import com.gangnam.wholesale.domain.product.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SupplierRepositoryImpl implements SupplierRepository {

	private final SpringDataJpaSupplierRepository jpaSupplierRepository;

	@Override
	public Optional<Supplier> findById(Long id) {
		return this.jpaSupplierRepository.findById(id);
	}

}
