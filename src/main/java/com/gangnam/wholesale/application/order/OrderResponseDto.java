package com.gangnam.wholesale.application.order;

import java.math.BigDecimal;
import java.util.List;

import com.gangnam.wholesale.domain.order.OrderStatus;
import com.gangnam.wholesale.domain.order.PaymentMethod;

public record OrderResponseDto(Long id, String orderNumber, OrderStatus orderStatus, PaymentMethod paymentMethod,
							   Long customerID, List<Item> items) {

	public record Item(Long id, Integer quantity, BigDecimal unitPrice, BigDecimal subTotalPrice, Long productId) { }
}
