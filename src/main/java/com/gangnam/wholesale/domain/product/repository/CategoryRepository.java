package com.gangnam.wholesale.domain.product.repository;

import java.util.List;
import java.util.Optional;

import com.gangnam.wholesale.domain.product.Category;

public interface CategoryRepository {
	Category save(Category category);
	Optional<Category> findById(Long id);
	Optional<Category> findByName(String name);
	List<Category> findByRootCategories();
}