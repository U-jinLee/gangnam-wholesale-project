package com.gangnam.wholesale.application.customer.mapper;

import java.util.List;

import com.gangnam.wholesale.application.account.DepositAccountResponseDto;
import com.gangnam.wholesale.application.customer.CustomerRequestDto;
import com.gangnam.wholesale.application.customer.CustomerResponseDto;
import com.gangnam.wholesale.domain.account.DepositAccount;
import com.gangnam.wholesale.domain.customer.Customer;
import com.gangnam.wholesale.domain.customer.CustomerTier;

public class CustomerMapper {

	private CustomerMapper() {
		throw new IllegalStateException("Utility class");
	}

	public static CustomerResponseDto toResponse(Customer customer, List<DepositAccount> depositAccounts) {
		List<DepositAccountResponseDto> accountResponses =
			depositAccounts.stream().map(DepositAccountResponseDto::from).toList();

		return new CustomerResponseDto(customer.getId(), customer.getEmail(), customer.getRegistrationNumber(),
			customer.getCreatedAt(), customer.getModifiedAt(), accountResponses);
	}

	public static Customer toEntity(CustomerRequestDto request, String encodedPassword, CustomerTier customerTier) {
		return Customer.builder()
			.email(request.email())
			.password(encodedPassword)
			.registrationNumber(request.registrationNumber())
			.customerTier(customerTier)
			.build();
	}

}