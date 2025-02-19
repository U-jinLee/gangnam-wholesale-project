package com.gangnam.wholesale.infrastructure.persistence.account;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.account.DepositAccount;
import com.gangnam.wholesale.domain.account.repository.DepositAccountRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DepositAccountRepositoryImpl implements DepositAccountRepository {

	private final SpringDataJpaDepositAccountRepository jpaDepositAccountRepository;

	@Override
	public List<DepositAccount> saveAll(List<DepositAccount> depositAccounts) {
		return this.jpaDepositAccountRepository.saveAll(depositAccounts);
	}

	@Override
	public List<DepositAccount> findByCustomerId(Long customerId) {
		return this.jpaDepositAccountRepository.findByCustomerId(customerId);
	}

}
