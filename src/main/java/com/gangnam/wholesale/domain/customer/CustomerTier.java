package com.gangnam.wholesale.domain.customer;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "customer_tiers")
@Entity
public class CustomerTier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private BigDecimal minimumDeposit;

	private BigDecimal discountRate;

	private Integer priority;

	private String description;

	@Builder
	public CustomerTier(String name, BigDecimal minimumDeposit, BigDecimal discountRate, Integer priority,
		String description) {
		this.name = name;
		this.minimumDeposit = minimumDeposit;
		this.discountRate = discountRate;
		this.priority = priority;
		this.description = description;
	}

}