package com.food.ordering.system.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class OrderItem {
	
	@NotNull
	private final UUID productId;
	@NotNull
	private final Integer quantity;
	@NotNull
	private final BigDecimal price;
	@NotNull
	private final BigDecimal subTotal;
	
	public UUID getProductId() {
		return productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	
	public OrderItem(@NotNull UUID productId, @NotNull Integer quantity, @NotNull BigDecimal price,
			@NotNull BigDecimal subTotal) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}
	
	

}
