package com.gangnam.wholesale.application.account;

import java.math.BigDecimal;

import com.gangnam.wholesale.domain.account.AccountType;
import com.gangnam.wholesale.domain.account.DepositAccount;
import com.gangnam.wholesale.domain.account.DepositStatus;

public record DepositAccountResponseDto(Long id, AccountType accountType, BigDecimal currentBalance,
										DepositStatus depositStatus) {
	public static DepositAccountResponseDto from(DepositAccount depositAccount) {
		return new DepositAccountResponseDto(
			depositAccount.getId(),
			depositAccount.getAccountType(),
			depositAccount.getCurrentBalance(),
			depositAccount.getDepositStatus());
	}
}