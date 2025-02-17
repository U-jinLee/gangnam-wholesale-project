package com.gangnam.wholesale.domain.order;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gangnam.wholesale.global.entity.BaseTimeEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {

	private static final String ORDER_DATE_FORMAT = "yyyyMMddHH";
	private static final String SEPARATOR = "_";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// yyyyMMddhh_id_serialNumber
	private String orderNumber;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	private Long customerId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	@Builder
	public Order(OrderStatus orderStatus, PaymentMethod paymentMethod, Long customerId) {
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.customerId = customerId;
	}

	public void createOrderNumber() {
		this.orderNumber = new StringBuilder()
			.append(this.id)
			.append(SEPARATOR)
			.append(getCreatedAt().format(DateTimeFormatter.ofPattern(ORDER_DATE_FORMAT)))
			.append(SEPARATOR)
			.append(this.orderItems.get(0).getSubTotalPrice())
			.toString();
	}

	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public void updateOrderStatus() {
		this.orderStatus = OrderStatus.CONFIRMED;
	}
}