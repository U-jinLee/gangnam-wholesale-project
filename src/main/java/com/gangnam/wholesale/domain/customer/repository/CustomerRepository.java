package com.gangnam.wholesale.domain.customer.repository;

import java.util.Optional;

import com.gangnam.wholesale.domain.customer.Customer;

public interface CustomerRepository {
	Customer save(Customer customer);
	Optional<Customer> findByEmail(String email);
}