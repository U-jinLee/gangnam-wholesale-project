package com.gangnam.wholesale.application.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangnam.wholesale.application.order.mapper.OrderMapper;
import com.gangnam.wholesale.domain.order.Order;
import com.gangnam.wholesale.domain.order.OrderItem;
import com.gangnam.wholesale.domain.order.repository.OrderRepository;
import com.gangnam.wholesale.domain.product.repository.ProductRepository;
import com.gangnam.wholesale.global.error.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	public OrderResponseDto getOrder(Long orderId) {

		Order order = this.orderRepository.findById(orderId).orElseThrow(() ->
			new EntityNotFoundException("Order not found with id: " + orderId));

		return OrderMapper.toResponse(order);
	}

	@Transactional
	public OrderResponseDto createOrder(OrderRequestDto request) {

		// todo: customer Id 로직 추가
		Long customerId = 1L;

		// 상품이 존재하는지 확인하는 로직
		request.items().forEach(item ->
			this.productRepository.findById(item.productId()).orElseThrow(() ->
				new EntityNotFoundException("Product not found with id: " + item.productId())));

		Order orderEntity = OrderMapper.toEntity(request, customerId);

		request.items().forEach(item -> {
			OrderItem orderItemEntity = OrderItem.builder()
				.productId(item.productId())
				.quantity(item.quantity())
				.unitPrice(item.unitPrice())
				.build();

			orderEntity.addOrderItem(orderItemEntity);
		});

		Order order = this.orderRepository.save(orderEntity);

		order.createOrderNumber();

		return OrderMapper.toResponse(order);
	}

}
