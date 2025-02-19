package com.gangnam.wholesale.infrastructure.persistence.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.account.DepositAccount;

@Repository
public interface SpringDataJpaDepositAccountRepository extends JpaRepository<DepositAccount, Long> {
	List<DepositAccount> findByCustomerId(Long customerId);
}