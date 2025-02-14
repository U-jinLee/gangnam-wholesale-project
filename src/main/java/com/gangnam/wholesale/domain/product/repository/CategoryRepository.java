package com.gangnam.wholesale.domain.product.repository;

import java.util.Optional;

import com.gangnam.wholesale.domain.product.Category;

public interface CategoryRepository {
	Optional<Category> findById(Long id);
}