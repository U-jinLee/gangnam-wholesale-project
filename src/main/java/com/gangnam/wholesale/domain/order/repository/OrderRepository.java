package com.gangnam.wholesale.domain.order.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gangnam.wholesale.domain.order.Order;

public interface OrderRepository {
	Order save(Order order);
	Optional<Order> findById(Long orderId);
	Page<Order> findAll(Pageable pageable);
}