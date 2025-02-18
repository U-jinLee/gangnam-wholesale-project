package com.gangnam.wholesale.presentation.admin.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangnam.wholesale.application.product.CategoryRequestDto;
import com.gangnam.wholesale.application.product.CategoryResponseDto;
import com.gangnam.wholesale.application.product.CategoryService;
import com.gangnam.wholesale.common.ApiMappingAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.ADMIN_CATEGORY_API)
@RestController
public class AdminCategoryApiController {

	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto request) {
		CategoryResponseDto result = this.categoryService.createCategory(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PostMapping(ApiMappingAttributes.SUB_CATEGORY_API)
	public ResponseEntity<CategoryResponseDto> addSubCategory(@PathVariable Long id,
		@RequestBody CategoryRequestDto request) {
		CategoryResponseDto result = this.categoryService.addSubCategory(id, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

}