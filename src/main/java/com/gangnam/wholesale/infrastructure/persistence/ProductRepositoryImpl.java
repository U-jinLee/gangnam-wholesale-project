package com.gangnam.wholesale.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final SpringDataJpaProductRepository jpaProductRepository;

	@Override
	public Product save(Product product) {
		return this.jpaProductRepository.save(product);
	}

}