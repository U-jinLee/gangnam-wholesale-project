package com.gangnam.wholesale.presentation.admin.product;

import com.gangnam.wholesale.application.product.ProductRequestDto;
import com.gangnam.wholesale.application.product.ProductResponseDto;
import com.gangnam.wholesale.application.product.ProductService;
import com.gangnam.wholesale.common.ApiMappingAttributes;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.ADMIN_PRODUCT_API)
@RestController
public class AdminProductApiController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<Page<ProductResponseDto>> getProducts(Pageable pageable) {
		Page<ProductResponseDto> result = this.productService.getProducts(pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
		ProductResponseDto result = this.productService.getProduct(id);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto request) {
		ProductResponseDto result = this.productService.createProduct(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

}