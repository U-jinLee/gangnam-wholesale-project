package com.gangnam.wholesale.presentation.admin.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangnam.wholesale.application.customer.CustomerRequestDto;
import com.gangnam.wholesale.application.customer.CustomerResponseDto;
import com.gangnam.wholesale.application.customer.CustomerService;
import com.gangnam.wholesale.common.ApiMappingAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.ADMIN_CUSTOMER_API)
@RestController
public class AdminCustomerApiController {

	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto request) {
		CustomerResponseDto result = this.customerService.createCustomer(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

}
