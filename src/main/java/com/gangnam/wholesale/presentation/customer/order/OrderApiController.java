package com.gangnam.wholesale.presentation.customer.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangnam.wholesale.application.order.OrderRequestDto;
import com.gangnam.wholesale.application.order.OrderResponseDto;
import com.gangnam.wholesale.application.order.OrderService;
import com.gangnam.wholesale.common.ApiMappingAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.ORDER_API)
@RestController
public class OrderApiController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto request) {
		OrderResponseDto result = this.orderService.createOrder(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

}