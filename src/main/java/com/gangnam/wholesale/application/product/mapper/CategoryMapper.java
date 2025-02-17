package com.gangnam.wholesale.application.product.mapper;

import com.gangnam.wholesale.application.product.CategoryRequestDto;
import com.gangnam.wholesale.application.product.CategoryResponseDto;
import com.gangnam.wholesale.domain.product.Category;

public class CategoryMapper {

	private CategoryMapper() {
		throw new IllegalStateException("Utility class");
	}

	public static CategoryResponseDto toResponse(Category category) {
		return CategoryResponseDto.from(category);
	}

	public static Category toEntity(CategoryRequestDto request) {
		return Category.builder()
			.name(request.name())
			.build();
	}

}