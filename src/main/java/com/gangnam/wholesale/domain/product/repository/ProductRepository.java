package com.gangnam.wholesale.domain.product.repository;

import com.gangnam.wholesale.domain.product.Product;

public interface ProductRepository {
	Product save(Product product);
}