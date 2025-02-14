package com.gangnam.wholesale.application.product;

import java.math.BigDecimal;

public record ProductRequestDto(String code,
								String name,
								String description,
								BigDecimal wholesaleCost,
								BigDecimal basePrice,
								Long supplierId,
								Long categoryId) {
}