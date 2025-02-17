package com.gangnam.wholesale.domain.customer.repository;

import java.util.List;

import com.gangnam.wholesale.domain.customer.CustomerTier;

public interface CustomerTierRepository {
	List<CustomerTier> findAll();
}
