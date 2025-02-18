package com.gangnam.wholesale.application.product;

import java.util.List;

import com.gangnam.wholesale.domain.product.Category;

public record CategoryResponseDto(Long id, String name, List<CategoryResponseDto> subCategories) {
	public static CategoryResponseDto from(Category category) {
		List<CategoryResponseDto> subCategories = category.getSubCategories()
			.stream().map(CategoryResponseDto::from).toList();
		return new CategoryResponseDto(category.getId(), category.getName(), subCategories);
	}
}