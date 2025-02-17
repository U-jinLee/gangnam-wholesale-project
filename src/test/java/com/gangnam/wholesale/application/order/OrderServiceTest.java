package com.gangnam.wholesale.application.order;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.gangnam.wholesale.domain.order.Order;
import com.gangnam.wholesale.domain.order.OrderItem;
import com.gangnam.wholesale.domain.order.OrderStatus;
import com.gangnam.wholesale.domain.order.PaymentMethod;
import com.gangnam.wholesale.domain.order.repository.OrderRepository;
import com.gangnam.wholesale.domain.product.Product;
import com.gangnam.wholesale.domain.product.repository.ProductRepository;
import com.gangnam.wholesale.global.error.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
	@InjectMocks
	private OrderService orderService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private OrderRepository orderRepository;

	private Product mockProduct;
	private OrderItem mockOrderItem;
	private Order mockOrder;

	@BeforeEach
	void setUp() {
		Long productId = 1L;
		String code = "12345";
		String name = "강남소주";
		String description = "강남인을 위한 15도 증류주";
		BigDecimal wholesaleCost = BigDecimal.valueOf(5000);
		BigDecimal basePrice = BigDecimal.valueOf(10000);

		this.mockProduct = Product.builder()
			.code(code)
			.name(name)
			.description(description)
			.wholesaleCost(wholesaleCost)
			.basePrice(basePrice)
			.build();

		ReflectionTestUtils.setField(this.mockProduct, "id", productId);

		this.mockOrderItem = OrderItem.builder()
			.unitPrice(this.mockProduct.getBasePrice())
			.quantity(5)
			.productId(this.mockProduct.getId())
			.build();

		ReflectionTestUtils.setField(this.mockOrderItem, "id", 1L);

		Long customerId = 1L;
		this.mockOrder = Order.builder()
			.orderStatus(OrderStatus.PENDING)
			.paymentMethod(PaymentMethod.LIQUOR_CARD)
			.customerId(customerId)
			.build();

		ReflectionTestUtils.setField(this.mockOrder, "id", 1L);
		ReflectionTestUtils.setField(this.mockOrder, "createdAt", LocalDateTime.now());

		this.mockOrder.addOrderItem(this.mockOrderItem);
		this.mockOrder.createOrderNumber();
	}

	@Test
	@DisplayName("주문을 정상적으로 조회한다.")
	void getOrder_Success() {
		//given
		Long orderId = 1L;
		//when
		when(this.orderRepository.findById(orderId)).thenReturn(Optional.of(this.mockOrder));
		OrderResponseDto response = this.orderService.getOrder(orderId);
		//then
		assertNotNull(response);
	}

	@Test
	@DisplayName("새로운 주문을 정상적으로 생성한다.")
	void createOrder_Success() {
		//given
		BigDecimal unitPrice = BigDecimal.valueOf(50000);
		Integer quantity = 20;

		OrderRequestDto.Item item = new OrderRequestDto.Item(this.mockProduct.getId(), unitPrice, quantity);
		List<OrderRequestDto.Item> items = List.of(item);

		OrderRequestDto request = new OrderRequestDto(PaymentMethod.LIQUOR_CARD, items);

		//when
		Mockito.when(this.productRepository.findById(this.mockProduct.getId())).thenReturn(Optional.of(this.mockProduct));
		Mockito.when(this.orderRepository.save(any(Order.class))).thenReturn(this.mockOrder);
		OrderResponseDto response = this.orderService.createOrder(request);
		//then
		assertNotNull(response);
	}

	@Test
	@DisplayName("존재하지 않는 Product일 경우 예외가 발생한다")
	void createOrder_Fail_ProductNotFound() {
		//given
		long productId = 1L;
		BigDecimal unitPrice = BigDecimal.valueOf(50000);
		int quantity = 20;

		OrderRequestDto.Item item = new OrderRequestDto.Item(productId, unitPrice, quantity);
		List<OrderRequestDto.Item> items = List.of(item);

		OrderRequestDto request = new OrderRequestDto(PaymentMethod.LIQUOR_CARD, items);
		Optional<Product> productResult = Optional.empty();

		//when
		Mockito.when(this.productRepository.findById(productId)).thenReturn(productResult);
		//then
		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
			() -> this.orderService.createOrder(request));

		assertNotNull(exception.getMessage());
	}

}