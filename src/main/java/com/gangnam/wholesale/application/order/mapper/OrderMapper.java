package com.gangnam.wholesale.application.order.mapper;

import java.util.List;

import com.gangnam.wholesale.application.order.OrderRequestDto;
import com.gangnam.wholesale.application.order.OrderResponseDto;
import com.gangnam.wholesale.domain.order.Order;
import com.gangnam.wholesale.domain.order.OrderStatus;

public class OrderMapper {

	private OrderMapper() {
		throw new IllegalStateException("Utility class");
	}

	public static OrderResponseDto toResponse(Order order) {
		List<OrderResponseDto.Item> items = order.getOrderItems()
			.stream()
			.map(item -> new OrderResponseDto.Item(item.getId(), item.getQuantity(), item.getUnitPrice(),
				item.getSubTotalPrice(), item.getProductId()))
			.toList();

		return new OrderResponseDto(order.getId(), order.getOrderNumber(), order.getOrderStatus(),
			order.getPaymentMethod(), order.getCustomerId(), items);
	}

	public static Order toEntity(OrderRequestDto request, Long customerId) {
		return Order.builder()
			.orderStatus(OrderStatus.PENDING)
			.paymentMethod(request.paymentMethod())
			.customerId(customerId)
			.build();
	}

}