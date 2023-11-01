package com.food.ordering.system.order.service.domain.dto.message;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.food.ordering.system.domain.valueobject.PaymentStatus;

public class PaymentResponse {
	
	private String id;
	private String sagaId;
	private String orderId;
	private String paymentId;
	private String customerId;
	private BigDecimal price;
	private Instant createdAt;
	private PaymentStatus paymentStatus;
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
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	public void setFailureMessages(List<String> failureMessages) {
		this.failureMessages = failureMessages;
	}
	
	

}
