package com.food.ordering.system.order.service.domain.dto.track;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.food.ordering.system.domain.valueobject.OrderStatus;

public class TrackOrderResponse {
	
	@NotNull
	private final UUID orderTrackingId;
	@NotNull
	private final OrderStatus orderStatus;
	private final List<String> failureMessages;
	
	
	
	public TrackOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
			List<String> failureMessages) {
		super();
		this.orderTrackingId = orderTrackingId;
		this.orderStatus = orderStatus;
		this.failureMessages = failureMessages;
	}
	
	public UUID getOrderTrackingId() {
		return orderTrackingId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	
	

}
