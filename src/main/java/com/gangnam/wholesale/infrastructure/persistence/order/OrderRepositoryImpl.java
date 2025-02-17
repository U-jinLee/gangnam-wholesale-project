package com.gangnam.wholesale.infrastructure.persistence.order;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.order.Order;
import com.gangnam.wholesale.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final SpringDataJpaOrderRepository jpaOrderRepository;

	@Override
	public Order save(Order order) {
		return this.jpaOrderRepository.save(order);
	}

	@Override
	public Optional<Order> findById(Long orderId) {
		return this.jpaOrderRepository.findById(orderId);
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return this.jpaOrderRepository.findAll(pageable);
	}

}
