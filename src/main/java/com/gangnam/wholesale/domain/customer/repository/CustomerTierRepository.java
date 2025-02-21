package com.gangnam.wholesale.domain.customer.repository;

import java.util.List;
import java.util.Optional;

import com.gangnam.wholesale.domain.customer.CustomerTier;
import com.gangnam.wholesale.domain.customer.TierName;

public interface CustomerTierRepository {
	List<CustomerTier> findAll();
	Optional<CustomerTier> findByTierName(TierName tierName);
}
