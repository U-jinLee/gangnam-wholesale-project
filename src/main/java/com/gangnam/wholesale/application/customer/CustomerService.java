package com.gangnam.wholesale.application.customer;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangnam.wholesale.application.customer.mapper.CustomerMapper;
import com.gangnam.wholesale.domain.account.DepositAccount;
import com.gangnam.wholesale.domain.account.repository.DepositAccountRepository;
import com.gangnam.wholesale.domain.customer.Customer;
import com.gangnam.wholesale.domain.customer.CustomerTier;
import com.gangnam.wholesale.domain.customer.TierName;
import com.gangnam.wholesale.domain.customer.repository.CustomerRepository;
import com.gangnam.wholesale.domain.customer.repository.CustomerTierRepository;
import com.gangnam.wholesale.global.error.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final PasswordEncoder passwordEncode;
	private final CustomerRepository customerRepository;
	private final DepositAccountRepository depositAccountRepository;
	private final CustomerTierRepository customerTierRepository;

	@Transactional
	public CustomerResponseDto createCustomer(CustomerRequestDto request) {

		CustomerTier customerTier = this.customerTierRepository.findByTierName(TierName.BRONZE)
			.orElseThrow(() -> new EntityNotFoundException("Customer tier not found"));

		Customer customerEntity = CustomerMapper.toEntity(request, this.passwordEncode.encode(request.password()),
			customerTier);

		Customer customer = this.customerRepository.save(customerEntity);

		List<DepositAccount> depositAccountEntities = DepositAccount.createDepositAccounts(customer.getId());
		List<DepositAccount> depositAccounts = this.depositAccountRepository.saveAll(depositAccountEntities);

		return CustomerMapper.toResponse(customer, depositAccounts);
	}

	@Transactional(readOnly = true)
	public CustomerResponseDto getCustomer(Long id) {

		Customer customer = this.customerRepository.findById(id).orElseThrow(() ->
			new EntityNotFoundException("Customer not found"));

		List<DepositAccount> depositAccounts = this.depositAccountRepository.findByCustomerId(id);

		return CustomerMapper.toResponse(customer, depositAccounts);
	}

}
