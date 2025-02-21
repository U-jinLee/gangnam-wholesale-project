package com.gangnam.wholesale.domain.customer;

import com.gangnam.wholesale.global.entity.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "customers")
@Entity
public class Customer extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	private String password;

	private String registrationNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_tier_id")
	private CustomerTier customerTier;

	@Builder
	public Customer(String email, String password, String registrationNumber, CustomerTier customerTier) {
		this.email = email;
		this.password = password;
		this.registrationNumber = registrationNumber;
		this.customerTier = customerTier;
	}

}