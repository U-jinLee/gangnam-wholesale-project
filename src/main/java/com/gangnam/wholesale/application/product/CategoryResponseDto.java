package com.gangnam.wholesale.application.product;

import com.gangnam.wholesale.domain.product.Category;

public record CategoryResponseDto(Long id, String name) {
	public static CategoryResponseDto from(Category category) {
		return new CategoryResponseDto(category.getId(), category.getName());
	}
}