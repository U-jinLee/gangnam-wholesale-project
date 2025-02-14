package com.gangnam.wholesale.application.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangnam.wholesale.application.product.mapper.ProductMapper;
import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.Supplier;
import com.gangnam.wholesale.domain.product.repository.CategoryRepository;
import com.gangnam.wholesale.domain.product.repository.ProductRepository;
import com.gangnam.wholesale.domain.product.repository.SupplierRepository;
import com.gangnam.wholesale.global.error.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final SupplierRepository supplierRepository;
	private final CategoryRepository categoryRepository;

	@Transactional
	public ProductResponseDto createProduct(ProductRequestDto request) {
		// 공급자 엔티티 찾기
		Supplier supplier = this.supplierRepository.findById(request.supplierId()).orElseThrow(() ->
			new EntityNotFoundException("Supplier not found"));

		// 카테고리 엔티티 찾기
		Category category = this.categoryRepository.findById(request.categoryId()).orElseThrow(() ->
			new EntityNotFoundException("Category not found"));

		// 상품 엔티티 생성
		Product productEntity = ProductMapper.toEntity(request, supplier, category);

		// 상품 저장
		Product product = this.productRepository.save(productEntity);

		// 상품 응답 DTO 생성
		return ProductMapper.toResponse(product);
	}

}