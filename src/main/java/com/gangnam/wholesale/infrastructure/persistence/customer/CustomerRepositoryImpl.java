package com.gangnam.wholesale.infrastructure.persistence.customer;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.customer.Customer;
import com.gangnam.wholesale.domain.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

	private final SpringDataJpaCustomerRepository jpaCustomerRepository;

	@Override
	public Customer save(Customer customer) {
		return this.jpaCustomerRepository.save(customer);
	}

	@Override
	public Optional<Customer> findByEmail(String email) {
		return this.jpaCustomerRepository.findByEmail(email);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return this.jpaCustomerRepository.findById(id);
	}

}
