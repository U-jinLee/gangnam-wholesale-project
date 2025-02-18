package com.gangnam.wholesale.infrastructure.persistence.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.customer.Customer;

@Repository
public interface SpringDataJpaCustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
}