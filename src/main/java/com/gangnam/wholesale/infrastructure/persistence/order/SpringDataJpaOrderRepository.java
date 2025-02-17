package com.gangnam.wholesale.infrastructure.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gangnam.wholesale.domain.order.Order;

@Repository
public interface SpringDataJpaOrderRepository extends JpaRepository<Order, Long> {
}