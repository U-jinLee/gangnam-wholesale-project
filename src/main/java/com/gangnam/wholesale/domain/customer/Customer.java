package com.gangnam.wholesale.domain.customer;

import com.gangnam.wholesale.global.entity.BaseTimeEntity;

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
@Table(name = "customers")
@Entity
public class Customer extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	private String password;

	private String registrationNumber;

	@Builder
	public Customer(String email, String password, String registrationNumber) {
		this.email = email;
		this.password = password;
		this.registrationNumber = registrationNumber;
	}

}