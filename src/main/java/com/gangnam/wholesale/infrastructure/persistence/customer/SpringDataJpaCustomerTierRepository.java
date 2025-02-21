package com.gangnam.wholesale.infrastructure.persistence.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.customer.CustomerTier;
import com.gangnam.wholesale.domain.customer.TierName;

@Repository
public interface SpringDataJpaCustomerTierRepository extends JpaRepository<CustomerTier, Long> {
	Optional<CustomerTier> findByTierName(TierName tierName);
}