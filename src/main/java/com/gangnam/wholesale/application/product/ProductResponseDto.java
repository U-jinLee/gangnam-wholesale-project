package com.gangnam.wholesale.application.product;

import java.math.BigDecimal;

public record ProductResponseDto(Long id, String code, String name, String description, BigDecimal wholesaleCost,
								 BigDecimal basePrice, SupplierResponseDto supplier, CategoryResponseDto category) {
}