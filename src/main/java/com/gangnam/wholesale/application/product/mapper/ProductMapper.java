package com.gangnam.wholesale.application.product.mapper;

import java.util.List;

import com.gangnam.wholesale.application.product.CategoryResponseDto;
import com.gangnam.wholesale.application.product.ProductPriceResponseDto;
import com.gangnam.wholesale.application.product.ProductRequestDto;
import com.gangnam.wholesale.application.product.ProductResponseDto;
import com.gangnam.wholesale.application.product.SupplierResponseDto;
import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.Supplier;

public class ProductMapper {

	private ProductMapper() {
		throw new IllegalStateException("Utility class");
	}

	public static ProductResponseDto toResponse(Product product) {
		List<ProductPriceResponseDto> productPrices = product.getProductPrices()
			.stream()
			.map(ProductPriceResponseDto::from)
			.toList();

		return new ProductResponseDto(
			product.getId(),
			product.getCode(),
			product.getName(),
			product.getDescription(),
			product.getWholesaleCost(),
			product.getBasePrice(),
			SupplierResponseDto.from(product.getSupplier()),
			CategoryResponseDto.from(product.getCategory()),
			productPrices);
	}

	public static Product toEntity(ProductRequestDto request, Supplier supplier, Category category) {
		return Product.builder()
			.code(request.code())
			.name(request.name())
			.description(request.description())
			.wholesaleCost(request.wholesaleCost())
			.basePrice(request.basePrice())
			.supplier(supplier)
			.category(category)
			.build();
	}

}