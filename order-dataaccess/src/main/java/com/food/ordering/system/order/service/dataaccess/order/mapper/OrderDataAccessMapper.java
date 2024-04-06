package com.food.ordering.system.order.service.dataaccess.order.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderAddressEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderItemEntity;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

@Component
public class OrderDataAccessMapper {

	public OrderEntity orderToOrderEntity(Order order) {

		OrderEntity orderEntity = OrderEntity.builder()
				.id(order.getId().getValue())
				.customerId(order.getCustomerId().getValue())
				.restaurantId(order.getRestaurantId().getValue())
				.address( deliveryAddressToAddressEntity( order.getDeliveryAdrress()))
				.price(order.getPrice().getAmount())
				.items(orderitemsToOrderItemEntities(order.getItems()))
				.orderStatus( order.getOrderStatus() )
				.failureMessages( CollectionUtils.isNotEmpty(order.getFailureMessages()) ? String.join( Order.FAILURE_MESSAGE_DELIMITER ) : "" )
				.build();

		orderEntity.getAddress().setOrder(orderEntity);
		orderEntity.getItems().forEach( orderItemEntity -> orderItemEntity.setOrder(orderEntity));

		return orderEntity;
	}

	public Order orderEntityToOrder(OrderEntity orderEntity) {
		
		
		Order order = Order.builder()
				.orderId( new OrderId(orderEntity.getId()))
				.customerId( new CustomerId(orderEntity.getCustomerId()))
				.restaurantId( new RestaurantId(orderEntity.getRestaurantId()))
				.deliveryAddress(addressEntityToDeliveryAddress( orderEntity.getAddress()))
				.price( new Money(orderEntity.getPrice()))
				.items( orderItemEntitiesToOrderItems( orderEntity.getItems() ))
				.trackingId( new TrackingId( orderEntity.getTrackingId()))
				.orderStatus( orderEntity.getOrderStatus() )
				.failureMessages( StringUtils.isNotEmpty(orderEntity.getFailureMessages()) ? new ArrayList<>(Arrays.asList( orderEntity.getFailureMessages().split( Order.FAILURE_MESSAGE_DELIMITER ))) : new ArrayList<>() )
				.build();

		return order;
	}

	private List<OrderItem> orderItemEntitiesToOrderItems(List<OrderItemEntity> items) {
		
		return items.stream().map( itemEntity -> new OrderItem( new OrderItemId( itemEntity.getId() ) , new Product( new ProductId( itemEntity.getProductId() ) ) , itemEntity.getQuantity() , new Money(itemEntity.getPrice()) , new Money(itemEntity.getSubTotal()) )).collect( Collectors.toList() );
	}

	private StreetAddress addressEntityToDeliveryAddress(OrderAddressEntity address) {
		
		return new StreetAddress(address.getId(), address.getStreet() , address.getPostalCode() , address.getCity());
	}

	private List<OrderItemEntity> orderitemsToOrderItemEntities(List<OrderItem> items) {

		return items.stream().map(orderItem -> OrderItemEntity.builder()
				.id(orderItem.getId().getValue())
				.productId(orderItem.getProduct().getId().getValue())
				.price(orderItem.getPrice().getAmount())
				.quantity(orderItem.getQuantity())
				.subTotal(orderItem.getSubTotal().getAmount())
				.build()).collect(Collectors.toList());
	}

	private OrderAddressEntity deliveryAddressToAddressEntity(StreetAddress deliveryAdrress) {

		return OrderAddressEntity.builder()
				.id(deliveryAdrress.getId())
				.city(deliveryAdrress.getCity())
				.postalCode(deliveryAdrress.getPostalCode())
				.street(deliveryAdrress.getStreet()).build();
	}

}
