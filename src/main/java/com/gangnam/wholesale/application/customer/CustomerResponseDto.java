package com.gangnam.wholesale.application.customer;

import java.time.LocalDateTime;
import java.util.List;

import com.gangnam.wholesale.application.account.DepositAccountResponseDto;

public record CustomerResponseDto(Long id, String email, String registrationNumber, LocalDateTime createdAt,
								  LocalDateTime modifiedAt, List<DepositAccountResponseDto> depositAccounts) {
}