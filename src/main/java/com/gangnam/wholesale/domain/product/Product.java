package com.gangnam.wholesale.domain.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "products")
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String name;

	private String description;

	private String imageUrl;

	//상품 매입가
	private BigDecimal wholesaleCost;

	//상품 기본 판매가
	private BigDecimal basePrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "product")
	private List<ProductPrice> productPrices = new ArrayList<>();

	@Builder
	public Product(String code,
		String name,
		String description,
		BigDecimal wholesaleCost,
		BigDecimal basePrice,
		Supplier supplier,
		Category category) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.wholesaleCost = wholesaleCost;
		this.basePrice = basePrice;
		this.supplier = supplier;
		this.category = category;
	}

	public void addProductPrice(ProductPrice productPrice) {
		this.productPrices.add(productPrice);
		productPrice.setProduct(this);
	}


}