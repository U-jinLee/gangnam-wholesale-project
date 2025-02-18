package com.gangnam.wholesale.presentation.customer.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangnam.wholesale.application.product.CategoryResponseDto;
import com.gangnam.wholesale.application.product.CategoryService;
import com.gangnam.wholesale.common.ApiMappingAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.CATEGORY_API)
@RestController
public class CategoryApiController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> getCategories() {
		List<CategoryResponseDto> result = this.categoryService.getCategories();
		return ResponseEntity.ok(result);
	}

}