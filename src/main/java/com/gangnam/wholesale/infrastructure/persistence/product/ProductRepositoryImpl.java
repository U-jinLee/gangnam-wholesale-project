package com.gangnam.wholesale.infrastructure.persistence.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final SpringDataJpaProductRepository jpaProductRepository;

	@Override
	public Optional<Product> findById(Long id) {
		return this.jpaProductRepository.findById(id);
	}

	@Override
	public Product save(Product product) {
		return this.jpaProductRepository.save(product);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return this.jpaProductRepository.findAll(pageable);
	}

}