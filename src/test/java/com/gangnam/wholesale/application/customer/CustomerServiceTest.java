package com.gangnam.wholesale.application.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import com.gangnam.wholesale.domain.account.DepositAccount;
import com.gangnam.wholesale.domain.account.repository.DepositAccountRepository;
import com.gangnam.wholesale.domain.customer.Customer;
import com.gangnam.wholesale.domain.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	@InjectMocks
	private CustomerService customerService;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private DepositAccountRepository depositAccountRepository;

	@Test
	@DisplayName("고객을 정상적으로 조회한다.")
	void getCustomer_Success() {
		//given
		String email = "test@test.com";
		String password = "12345";
		String registrationNumber = "111-11-11111";

		long customerId = 1L;
		Customer mockCustomer = Customer.builder()
			.email(email)
			.password(password)
			.registrationNumber(registrationNumber)
			.build();
		ReflectionTestUtils.setField(mockCustomer, "id", customerId);
		List<DepositAccount> mockDepositAccounts = DepositAccount.createDepositAccounts(customerId);
		//when
		when(this.customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
		when(this.depositAccountRepository.findByCustomerId(customerId)).thenReturn(mockDepositAccounts);
		CustomerResponseDto response = this.customerService.getCustomer(customerId);
		//then
		assertNotNull(response);
		System.out.println(response);
	}

	@Test
	@DisplayName("고객을 정상적으로 생성한다.")
	void createCustomer_Success() {
		//given
		String email = "test@test.com";
		String password = "12345";
		String registrationNumber = "111-11-11111";

		CustomerRequestDto request = new CustomerRequestDto(email, password, registrationNumber);
		long customerId = 1L;
		Customer mockCustomer = Customer.builder()
			.email(email)
			.password(password)
			.registrationNumber(registrationNumber)
			.build();
		ReflectionTestUtils.setField(mockCustomer, "id", customerId);
		List<DepositAccount> mockDepositAccounts = DepositAccount.createDepositAccounts(customerId);
		//when
		when(this.passwordEncoder.encode(anyString())).thenReturn(password);
		when(this.customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);
		when(this.depositAccountRepository.saveAll(any(List.class))).thenReturn(mockDepositAccounts);
		CustomerResponseDto response = this.customerService.createCustomer(request);
		//then
		assertNotNull(response);
		assertEquals(customerId, response.id());
		assertEquals(email, response.email());
		assertEquals(registrationNumber, response.registrationNumber());
		assertEquals(mockDepositAccounts.size(), response.depositAccounts().size());
	}

}