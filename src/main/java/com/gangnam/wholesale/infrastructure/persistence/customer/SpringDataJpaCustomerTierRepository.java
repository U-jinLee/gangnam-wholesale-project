package com.gangnam.wholesale.infrastructure.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.customer.CustomerTier;

@Repository
public interface SpringDataJpaCustomerTierRepository extends JpaRepository<CustomerTier, Long> {
}