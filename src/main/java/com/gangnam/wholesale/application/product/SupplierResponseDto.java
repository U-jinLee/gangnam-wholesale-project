package com.gangnam.wholesale.application.product;

import com.gangnam.wholesale.domain.product.Supplier;

public record SupplierResponseDto(Long id, String name) {
	public static SupplierResponseDto from(Supplier supplier) {
		return new SupplierResponseDto(supplier.getId(), supplier.getName());
	}
}