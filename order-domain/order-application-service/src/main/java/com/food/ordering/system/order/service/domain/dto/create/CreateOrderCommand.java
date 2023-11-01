package com.food.ordering.system.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class CreateOrderCommand {
	
	@NotNull
	private final UUID customerId;
	@NotNull
	private final UUID restaurantId;
	@NotNull
	private final BigDecimal price;
	@NotNull
	private final List<OrderItem> items;
	@NotNull
	private final OrderAddress address;
	
	public CreateOrderCommand(@NotNull UUID customerId, @NotNull UUID restaurantId, @NotNull BigDecimal price,
			@NotNull List<OrderItem> items, @NotNull OrderAddress address) {
		super();
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.price = price;
		this.items = items;
		this.address = address;
	}
	
	public UUID getCustomerId() {
		return customerId;
	}
	public UUID getRestaurantId() {
		return restaurantId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public OrderAddress getAddress() {
		return address;
	}
	
	
	
	

}
