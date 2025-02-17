package com.gangnam.wholesale.infrastructure.persistence.product;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.ProductPrice;
import com.gangnam.wholesale.domain.product.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

	private final SpringDataJpaProductPriceRepository jpaProductPriceRepository;

	@Override
	public ProductPrice save(ProductPrice productPrice) {
		return this.jpaProductPriceRepository.save(productPrice);
	}
}