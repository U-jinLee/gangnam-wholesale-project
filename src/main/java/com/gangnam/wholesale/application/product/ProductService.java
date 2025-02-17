package com.gangnam.wholesale.application.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangnam.wholesale.application.product.mapper.ProductMapper;
import com.gangnam.wholesale.domain.customer.CustomerTier;
import com.gangnam.wholesale.domain.customer.repository.CustomerTierRepository;
import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.ProductPrice;
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
	private final CustomerTierRepository customerTierRepository;

	@Transactional(readOnly = true)
	public Page<ProductResponseDto> getProducts(Pageable pageable) {
		return this.productRepository.findAll(pageable).map(ProductMapper::toResponse);
	}

	@Transactional(readOnly = true)
	public ProductResponseDto getProduct(Long id) {
		Product product = this.productRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Product not found" + id));

		return ProductMapper.toResponse(product);
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

		//고객 등급에 따른 할인률 적용 로직 구현
		this.customerTierRepository.findAll().forEach(tier ->
			productEntity.addProductPrice(calculateProductPrice(productEntity.getBasePrice(), tier)));

		// 상품 저장
		Product product = this.productRepository.save(productEntity);

		// 상품 응답 DTO 생성
		return ProductMapper.toResponse(product);
	}

	private ProductPrice calculateProductPrice(BigDecimal basePrice, CustomerTier tier) {
		BigDecimal discountPrice = basePrice
			.multiply(BigDecimal.ONE.subtract(tier.getDiscountRate().divide(BigDecimal.valueOf(100))));

		return ProductPrice.builder()
			.price(discountPrice)
			.customerTierId(tier.getId())
			.build();
	}

}