package com.gangnam.wholesale.application.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional(readOnly = true)
	public Page<ProductResponseDto> getProducts(Pageable pageable) {
		return this.productRepository.findAll(pageable).map(ProductMapper::toResponse);
	}

	@Transactional
	public ProductResponseDto createProduct(ProductRequestDto request) {
		// todo: 상품 도메인 로직 구현

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

		//todo: 고객 등급에 따른 할인률 적용 로직 구현

		// 상품 응답 DTO 생성
		return ProductMapper.toResponse(product);
	}

}