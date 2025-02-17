package com.gangnam.wholesale.application.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangnam.wholesale.application.product.mapper.CategoryMapper;
import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public CategoryResponseDto createCategory(CategoryRequestDto request) {
		// todo: Exception handling
		if (this.categoryRepository.findByName(request.name()).isPresent())
			throw new IllegalArgumentException("Category name already exists");

		Category category = this.categoryRepository.save(CategoryMapper.toEntity(request));

		return CategoryMapper.toResponse(category);
	}
}