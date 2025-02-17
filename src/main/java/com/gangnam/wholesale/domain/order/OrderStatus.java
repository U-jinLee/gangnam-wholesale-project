package com.gangnam.wholesale.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("결제대기"),
    CONFIRMED("결제확인"),
    CANCELLED("결제취소"),
    DELIVERED("배달완료");

    private final String explanation;
}
