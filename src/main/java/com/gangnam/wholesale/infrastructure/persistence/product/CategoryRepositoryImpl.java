package com.gangnam.wholesale.infrastructure.persistence.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	private final SpringDataJpaCategoryRepository jpaCategoryRepository;

	@Override
	public Category save(Category category) {
		return this.jpaCategoryRepository.save(category);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return this.jpaCategoryRepository.findById(id);
	}

	@Override
	public Optional<Category> findByName(String name) {
		return this.jpaCategoryRepository.findByName(name);
	}

	@Override
	public List<Category> findByRootCategories() {
		return this.jpaCategoryRepository.findAllByParentCategoryIsNull();
	}

}
