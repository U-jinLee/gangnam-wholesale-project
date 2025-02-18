package com.gangnam.wholesale.application.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

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
			.name("와인")
			.build();

		ReflectionTestUtils.setField(mockParentCategory, "id", categoryId);

		CategoryRequestDto request = new CategoryRequestDto("레드와인");

		//when
		when(this.categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockParentCategory));
		CategoryResponseDto response = this.categoryService.addSubCategory(categoryId, request);

		//then
		assertNotNull(response);
		System.out.println(response);

	}
}