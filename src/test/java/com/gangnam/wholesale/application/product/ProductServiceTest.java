package com.gangnam.wholesale.application.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

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

	private List<Product> products = new ArrayList<>();

	@BeforeEach
	void setUp() {
		Supplier supplier = Supplier.builder().name("제국주점").build();
		Category category = Category.builder().name("소주").build();

		this.products = List.of(
			Product.builder()
				.code("12345")
				.name("카디안 소주")
				.description("카디안의 카오스 에너지가 흠뻑 적셔진 소주!")
				.supplier(supplier)
				.category(category)
				.build(),
			Product.builder()
				.code("12346")
				.name("울트라마 소주")
				.description("울트라마의 엄격한 정제를 거친 소주")
				.supplier(supplier)
				.category(category)
				.build()
		);
	}

	@Test
	@DisplayName("상품 목록을 정상적으로 조회한다.")
	void getProducts_ShouldReturnPagedProductResponse() {
		//given
		PageRequest pageable = PageRequest.of(0, 2, Sort.by("name").ascending());

		PageImpl<Product> mockProductsPage = new PageImpl<>(this.products, pageable, this.products.size());

		//when
		when(this.productRepository.findAll(any(Pageable.class))).thenReturn(mockProductsPage);
		Page<ProductResponseDto> response = this.productService.getProducts(pageable);
		//then
		assertNotNull(response);
		assertEquals(this.products.size(), response.getTotalElements());
		assertEquals(1, response.getTotalPages());

	}

	@Test
	@DisplayName("상품 상세 정보를 정상적으로 가져온다.")
	void getProduct() {
		//given
		Long productId = 1L;
		String code = "12345";
		String name = "강남소주";
		String description = "강남인을 위한 15도 증류주";
		BigDecimal wholesaleCost = BigDecimal.valueOf(5000);
		BigDecimal basePrice = BigDecimal.valueOf(10000);

		Supplier mockSupplier = Supplier.builder()
			.name("제국주류")
			.build();

		Category mockCategory = Category.builder()
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

		ReflectionTestUtils.setField(mockProduct, "id", productId);
		//when
		when(this.productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));
		ProductResponseDto response = this.productService.getProduct(productId);
		//then
		assertNotNull(response);
		assertEquals(productId, response.id());
		assertEquals(code, response.code());

		verify(productRepository, times(1)).findById(1L);
	}

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
			.name("제국주류")
			.build();

		Category mockCategory = Category.builder()
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
		when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(mockSupplier));
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
		when(productRepository.save(any(Product.class))).thenReturn(mockProduct);

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
		when(supplierRepository.findById(supplierId)).thenReturn(Optional.empty());

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
			.name("제국주류")
			.build();

		//when
		when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(mockSupplier));
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

		//then
		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
			() -> productService.createProduct(request));
		assertNotNull(exception.getMessage());

	}

}