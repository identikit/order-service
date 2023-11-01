package com.food.ordering.system.order.service.domain.dto.create;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

public class CreateOrderResponse {
	
	
	private final UUID orderTrackingId;
	private final OrderStatus orderStatus;
	private final String message;
	
	public CreateOrderResponse(UUID orderTrackingId, OrderStatus orderStatus, String message) {
		super();
		this.orderTrackingId = orderTrackingId;
		this.orderStatus = orderStatus;
		this.message = message;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public String getMessage() {
		return message;
	}
	
	public UUID getOrderTrackingId() {
		return orderTrackingId;
	}
	

}
