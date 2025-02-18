package com.gangnam.wholesale.domain.account;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "deposit_accounts")
@Entity
public class DepositAccount {

	@Id
	private Long id;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private BigDecimal currentBalance;

	@Enumerated(EnumType.STRING)
	private DepositStatus depositStatus;

	private Long customerId;

	@Builder
	public DepositAccount(Long id, AccountType accountType, BigDecimal currentBalance, DepositStatus depositStatus,
		Long customerId) {
		this.id = id;
		this.accountType = accountType;
		this.currentBalance = currentBalance;
		this.depositStatus = depositStatus;
		this.customerId = customerId;
	}

	public static List<DepositAccount> createDepositAccounts(Long customerId) {
		return List.of(
			DepositAccount.builder()
				.accountType(AccountType.PERSONAL)
				.currentBalance(BigDecimal.ZERO)
				.depositStatus(DepositStatus.ACTIVE)
				.customerId(customerId)
				.build(),
			DepositAccount.builder()
				.accountType(AccountType.PLATFORM)
				.currentBalance(BigDecimal.ZERO)
				.depositStatus(DepositStatus.ACTIVE)
				.customerId(customerId)
				.build());
	}
}