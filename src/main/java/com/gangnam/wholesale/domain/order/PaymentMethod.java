package com.gangnam.wholesale.domain.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentMethod {
    LIQUOR_CARD("주류카드"),
    BANK_TRANSFER("계좌이체"),
    DEPOSIT_ACCOUNT("무통장입금");

    private final String explanation;
}