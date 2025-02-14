package com.gangnam.wholesale.application.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.Supplier;
import com.gangnam.wholesale.domain.product.repository.CategoryRepository;
import com.gangnam.wholesale.domain.product.repository.ProductRepository;
import com.gangnam.wholesale.domain.product.repository.SupplierRepository;
import com.gangnam.wholesale.global.error.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private SupplierRepository supplierRepository;

	@Mock
	CategoryRepository categoryRepository;

	@Test
	@DisplayName("새로운 상품을 정상적으로 생성한다.")
	void createProduct_Success() {
		//given
		String code = "12345";
		String name = "강남소주";
		String description = "강남인을 위한 15도 증류주";
		BigDecimal wholesaleCost = BigDecimal.valueOf(5000);
		BigDecimal basePrice = BigDecimal.valueOf(10000);
		Long supplierId = 1L;
		Long categoryId = 1L;

		ProductRequestDto request = new ProductRequestDto(code,
			name,
			description,
			wholesaleCost,
			basePrice,
			supplierId,
			categoryId);

		Supplier mockSupplier = Supplier.builder()
			.id(supplierId)
			.name("제국주류")
			.build();

		Category mockCategory = Category.builder()
			.id(categoryId)
			.name("소주")
			.build();

		Product mockProduct = Product.builder()
			.code(code)
			.name(name)
			.description(description)
			.wholesaleCost(wholesaleCost)
			.basePrice(basePrice)
			.supplier(mockSupplier)
			.category(mockCategory)
			.build();

		//when
		Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(mockSupplier));
		Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
		Mockito.when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

		ProductResponseDto response = productService.createProduct(request);
		//then
		assertNotNull(response);
		assertEquals(code, response.code());
		assertEquals(name, response.name());
		assertEquals(description, response.description());
		assertEquals(wholesaleCost, response.wholesaleCost());
		assertEquals(basePrice, response.basePrice());
		assertNotNull(response.supplier());
		assertNotNull(response.category());
	}

	@Test
	@DisplayName("존재하지 않는 Supplier일 경우 예외가 발생한다.")
	void createProduct_Fail_SupplierNotFound() {
		//given
		String code = "12345";
		String name = "강남소주";
		String description = "강남인을 위한 15도 증류주";
		BigDecimal wholesaleCost = BigDecimal.valueOf(5000);
		BigDecimal basePrice = BigDecimal.valueOf(10000);
		Long supplierId = 999L;
		Long categoryId = 1L;

		ProductRequestDto request = new ProductRequestDto(code,
			name,
			description,
			wholesaleCost,
			basePrice,
			supplierId,
			categoryId);

		//when
		Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

		//then
		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
			() -> productService.createProduct(request));
		assertNotNull(exception.getMessage());

	}

	@Test
	@DisplayName("존재하지 않는 Category일 경우 예외가 발생한다.")
	void createProduct_Fail_CategoryNotFound() {
		//given
		String code = "12345";
		String name = "강남소주";
		String description = "강남인을 위한 15도 증류주";
		BigDecimal wholesaleCost = BigDecimal.valueOf(5000);
		BigDecimal basePrice = BigDecimal.valueOf(10000);
		Long supplierId = 1L;
		Long categoryId = 999L;

		ProductRequestDto request = new ProductRequestDto(code,
			name,
			description,
			wholesaleCost,
			basePrice,
			supplierId,
			categoryId);

		Supplier mockSupplier = Supplier.builder()
			.id(supplierId)
			.name("제국주류")
			.build();


		//when
		Mockito.when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(mockSupplier));
		Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

		//then
		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
			() -> productService.createProduct(request));
		assertNotNull(exception.getMessage());

	}
}