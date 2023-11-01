package com.food.ordering.system.order.service.domain.dto.message;

import java.time.Instant;
import java.util.List;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;

public class RestaurantApprovalResponse {
	
	private String id;
	private String sagaId;
	private String orderId;
	private String restaurantId;
	private Instant createdAt;
	private OrderApprovalStatus orderApprovalStatus;
	private List<String> failureMessages;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSagaId() {
		return sagaId;
	}
	public void setSagaId(String sagaId) {
		this.sagaId = sagaId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public OrderApprovalStatus getOrderApprovalStatus() {
		return orderApprovalStatus;
	}
	public void setOrderApprovalStatus(OrderApprovalStatus orderApprovalStatus) {
		this.orderApprovalStatus = orderApprovalStatus;
	}
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	public void setFailureMessages(List<String> failureMessages) {
		this.failureMessages = failureMessages;
	}
	
	
	

}
