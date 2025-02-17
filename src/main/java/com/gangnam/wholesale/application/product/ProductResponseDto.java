package com.gangnam.wholesale.application.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDto(Long id, String code, String name, String description, String imageUrl,
								 BigDecimal wholesaleCost, BigDecimal basePrice, SupplierResponseDto supplier,
								 CategoryResponseDto category, List<ProductPriceResponseDto> productPrices,
								 LocalDateTime createdAt, LocalDateTime modifiedAt) {
}