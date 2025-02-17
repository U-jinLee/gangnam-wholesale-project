package com.gangnam.wholesale.domain.product.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gangnam.wholesale.domain.product.Product;

public interface ProductRepository {
	Optional<Product> findById(Long id);
	Product save(Product product);
	Page<Product> findAll(Pageable pageable);
}