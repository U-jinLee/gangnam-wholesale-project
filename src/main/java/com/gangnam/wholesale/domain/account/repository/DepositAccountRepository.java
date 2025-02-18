package com.gangnam.wholesale.domain.account.repository;

import java.util.List;

import com.gangnam.wholesale.domain.account.DepositAccount;

public interface DepositAccountRepository {
	List<DepositAccount> saveAll(List<DepositAccount> depositAccounts);
}