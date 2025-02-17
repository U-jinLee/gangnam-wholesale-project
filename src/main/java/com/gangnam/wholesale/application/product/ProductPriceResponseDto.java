package com.gangnam.wholesale.application.product;

import java.math.BigDecimal;

import com.gangnam.wholesale.domain.product.ProductPrice;

public record ProductPriceResponseDto(Long id, BigDecimal price) {

	public static ProductPriceResponseDto from(ProductPrice productPrice) {
		return new ProductPriceResponseDto(productPrice.getId(), productPrice.getPrice());
	}

}