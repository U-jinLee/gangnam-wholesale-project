package com.gangnam.wholesale.application.order;

import java.math.BigDecimal;
import java.util.List;

import com.gangnam.wholesale.domain.order.PaymentMethod;

public record OrderRequestDto(PaymentMethod paymentMethod, List<Item> items) {

	public record Item(Long productId, BigDecimal unitPrice, Integer quantity) {}

}