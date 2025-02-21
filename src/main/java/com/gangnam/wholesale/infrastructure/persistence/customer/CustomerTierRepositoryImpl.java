package com.gangnam.wholesale.infrastructure.persistence.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.customer.CustomerTier;
import com.gangnam.wholesale.domain.customer.TierName;
import com.gangnam.wholesale.domain.customer.repository.CustomerTierRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomerTierRepositoryImpl implements CustomerTierRepository {

	private final SpringDataJpaCustomerTierRepository jpaCustomerTierRepository;

	@Override
	public List<CustomerTier> findAll() {
		return this.jpaCustomerTierRepository.findAll();
	}

	@Override
	public Optional<CustomerTier> findByTierName(TierName tierName) {
		return Optional.empty();
	}

}
