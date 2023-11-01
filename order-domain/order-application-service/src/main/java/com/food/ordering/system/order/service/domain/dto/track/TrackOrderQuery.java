package com.food.ordering.system.order.service.domain.dto.track;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class TrackOrderQuery {
	
	@NotNull
	private final UUID orderTrackingId;
	
	public TrackOrderQuery(@NotNull UUID orderTrackingId) {
		super();
		this.orderTrackingId = orderTrackingId;
	}
	
	public UUID getOrderTrackingId() {
		return orderTrackingId;
	}
	
	

}
