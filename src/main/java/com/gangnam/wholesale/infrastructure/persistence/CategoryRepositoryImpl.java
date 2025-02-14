package com.gangnam.wholesale.infrastructure.persistence;

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
	public Optional<Category> findById(Long id) {
		return this.jpaCategoryRepository.findById(id);
	}

}
