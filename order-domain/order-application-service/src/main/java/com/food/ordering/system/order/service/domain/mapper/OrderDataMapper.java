package com.food.ordering.system.order.service.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;

@Component
public class OrderDataMapper {

	public Restaurant createOrderCommandTorestaurant(CreateOrderCommand createOrderCommand) {

		Restaurant restaurant = new Restaurant( createOrderCommand.getItems().stream().map( orderItem -> new Product( new ProductId( orderItem.getProductId() ))).collect( Collectors.toList()) );
		restaurant.setId( new RestaurantId(createOrderCommand.getRestaurantId() ) );

		return restaurant;
	}

	public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
		
		Order order = new Order( new CustomerId( createOrderCommand.getCustomerId() ) , 
				new RestaurantId( createOrderCommand.getRestaurantId() ) , 
				orderAddressToStreetAddress( createOrderCommand.getAddress() ) , 
				new Money( createOrderCommand.getPrice() ) , 
				orderItemsToorderItemEntities( createOrderCommand.getItems() ), 
				null, 
				null, 
				null, 
				null);

		return order;
	}

	private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
		
		StreetAddress streetAddress = new StreetAddress( UUID.randomUUID() , address.getStreet() , address.getPostalCode() , address.getCity() );
		return streetAddress;
	}

	private List<OrderItem> orderItemsToorderItemEntities(
			List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
		
		return orderItems.stream().map( orderItem -> new OrderItem(null , new Product( new ProductId( orderItem.getProductId() ) ) , orderItem.getQuantity() , new Money( orderItem.getPrice() ) , new Money( orderItem.getSubTotal() ) ) ).collect(Collectors.toList());
	}

	public CreateOrderResponse orderToCreateOrderResponse(Order order) {
		CreateOrderResponse createOderResponse = new CreateOrderResponse(order.getTrackingId().getValue() , order.getOrderStatus() , null);
		return createOderResponse;
		
	}

}
