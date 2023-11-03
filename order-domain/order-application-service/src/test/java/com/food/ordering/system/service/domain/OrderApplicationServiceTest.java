package com.food.ordering.system.service.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.create.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {
	
	@Autowired
	private OrderApplicationService orderApplicationService;
	
	@Autowired
	private OrderDataMapper orderDataMapper;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	private CreateOrderCommand createOrderCommand;
	private CreateOrderCommand createOrderCommandWrongPrice;
	private CreateOrderCommand createOrderCommandWrongProductPrice;
	private final UUID CUSTOMER_ID= UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
	private final UUID RESTAURANT_ID= UUID.fromString("018b2f19-e79e-7d6a-a56d-29feb6211b04");
	private final UUID PRODUCT_ID= UUID.fromString("38284410-7a40-11ee-b962-0242ac120002");
	private final UUID ORDER_ID= UUID.fromString("49d33daa-7a40-11ee-b962-0242ac120002");
	private final BigDecimal PRICE = new BigDecimal("200.00");
	
	@BeforeAll
	public void init() {
		
		List<OrderItem> items = new ArrayList<>();
		OrderItem item1 = new OrderItem( PRODUCT_ID , 1 , new BigDecimal("50.00") , new BigDecimal("50.00"));
		OrderItem item2 = new OrderItem( PRODUCT_ID , 3 , new BigDecimal("50.00") , new BigDecimal("150.00"));
		items.add(item1);
		items.add(item2);
		
		List<OrderItem> items2 = new ArrayList<>();
		OrderItem item3 = new OrderItem( PRODUCT_ID , 1 , new BigDecimal("60.00") , new BigDecimal("60.00"));
		OrderItem item4 = new OrderItem( PRODUCT_ID , 3 , new BigDecimal("50.00") , new BigDecimal("150.00"));
		items.add(item3);
		items.add(item4);
		
		OrderAddress orderAddress = new OrderAddress("street_1", "1000AB" , "Paris");
		
		createOrderCommand = new CreateOrderCommand( CUSTOMER_ID , RESTAURANT_ID , PRICE , items , orderAddress );
		
		createOrderCommandWrongPrice = new CreateOrderCommand( CUSTOMER_ID , RESTAURANT_ID , new BigDecimal("250.00") , items , orderAddress );
		
		createOrderCommandWrongProductPrice = new CreateOrderCommand( CUSTOMER_ID , RESTAURANT_ID , new BigDecimal("210.00") , items2 , orderAddress );
		
		Customer customer = new Customer();
		customer.setId(new CustomerId(CUSTOMER_ID));
		
		Restaurant restaurantResponse = new Restaurant( Arrays.asList(( new Product(new ProductId(PRODUCT_ID) , "product-1" , new Money( new BigDecimal("50.00") ) ) ) ,  
				( new Product(new ProductId(PRODUCT_ID) , "product-2" , new Money( new BigDecimal("50.00") ) ) )) );
		restaurantResponse.setActive(true);
		restaurantResponse.setId( new RestaurantId( createOrderCommand.getRestaurantId() ) );
		
		Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
		order.setId( new OrderId(ORDER_ID));
		
		when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
		when(restaurantRepository.findRestaurantInformation( orderDataMapper.createOrderCommandTorestaurant(createOrderCommand) )).thenReturn(Optional.of(restaurantResponse));
		
		when( orderRepository.save( any( Order.class ) ) ).thenReturn(order);
	}
	
	@Test
	public void testCreateOrder() {
		CreateOrderResponse createOrderRespone = orderApplicationService.createOrder(createOrderCommand);
		assertEquals( createOrderRespone.getOrderStatus() , OrderStatus.PENDING);
		assertEquals( createOrderRespone.getMessage() , "Order created successfully");
		assertNotNull( createOrderRespone.getOrderTrackingId());
	}
	

}
