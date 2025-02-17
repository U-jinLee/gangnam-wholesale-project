package com.gangnam.wholesale.presentation.admin.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gangnam.wholesale.application.order.OrderResponseDto;
import com.gangnam.wholesale.application.order.OrderService;
import com.gangnam.wholesale.common.ApiMappingAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(ApiMappingAttributes.ADMIN_ORDER_API)
@RestController
public class AdminOrderApiController {

	private final OrderService orderService;

	@GetMapping(ApiMappingAttributes.ID)
	public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id) {
		OrderResponseDto result = this.orderService.getOrder(id);
		return ResponseEntity.ok(result);
	}

}