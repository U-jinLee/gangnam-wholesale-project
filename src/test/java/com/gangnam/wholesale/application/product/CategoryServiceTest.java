package com.gangnam.wholesale.application.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.gangnam.wholesale.domain.product.Category;
import com.gangnam.wholesale.domain.product.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@InjectMocks
	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	private List<Category> mockCategories;

	@BeforeEach
	void setUp() {

		Category category1 = Category.builder()
			.name("와인")
			.build();
		ReflectionTestUtils.setField(category1, "id", 1L);

		Category category2 = Category.builder()
			.name("양주")
			.build();
		ReflectionTestUtils.setField(category2, "id", 2L);

		Category subCategory1 = Category.builder()
			.name("화이트")
			.build();

		Category subCategory2 = Category.builder()
			.name("레드")
			.build();

		Category subCategory3 = Category.builder()
			.name("위스키")
			.build();

		category1.addSubCategory(subCategory1);
		category1.addSubCategory(subCategory2);
		category2.addSubCategory(subCategory3);

		this.mockCategories = List.of(category1, category2);
	}

	@Test
	@DisplayName("새로운 카테고리를 정상적으로 생성한다")
	void createCategory_Success() {
		//given
		String categoryName = "소주";
		CategoryRequestDto request = new CategoryRequestDto(categoryName);

		Category mockCategory = Category.builder()
			.name(categoryName)
			.build();

		ReflectionTestUtils.setField(mockCategory, "id", 1L);

		//when
		when(this.categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());
		when(this.categoryRepository.save(any(Category.class))).thenReturn(mockCategory);
		CategoryResponseDto response = this.categoryService.createCategory(request);
		//then
		assertNotNull(response);
		assertEquals(categoryName, response.name());
	}

	@Test
	@DisplayName("부모 카테고리에 하위 카테고리를 정상적으로 추가한다")
	void addSubCategory_Success() {
		//given
		long categoryId = 1L;
		Category mockParentCategory = Category.builder()
			.name("기타")
			.build();

		ReflectionTestUtils.setField(mockParentCategory, "id", categoryId);

		CategoryRequestDto request = new CategoryRequestDto("리큐르");

		//when
		when(this.categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockParentCategory));
		CategoryResponseDto response = this.categoryService.addSubCategory(categoryId, request);

		//then
		assertNotNull(response);
		System.out.println(response);

	}

	@Test
	@DisplayName("카테고리 목록을 성공적으로 조회한다")
	void getCategories_Success() {
		//given
		//when
		when(this.categoryRepository.findByRootCategories()).thenReturn(this.mockCategories);
		List<CategoryResponseDto> response = this.categoryService.getCategories();
		//then
		assertNotNull(response);
		assertEquals(2, response.size());
	}
}