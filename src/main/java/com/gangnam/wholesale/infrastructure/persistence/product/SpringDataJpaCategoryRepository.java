package com.gangnam.wholesale.infrastructure.persistence.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.product.Category;

@Repository
public interface SpringDataJpaCategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);
}
