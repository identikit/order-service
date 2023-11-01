package com.food.ordering.system.order.service.domain.entity;

import java.math.BigDecimal;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId>{
	
	private OrderId orderId;
	private final Product product;
	private final int quantity;
	private final Money price;
	private final Money subTotal;
	
	
	
	
	public OrderItem(OrderItemId orderItemId , Product product, int quantity, Money price, Money subTotal) {
		super.setId( orderItemId );
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}
	
	public OrderId getOrderId() {
		return orderId;
	}
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
	public Money getPrice() {
		return price;
	}
	public Money getSubTotal() {
		return subTotal;
	}

	void initializeOrderItem(OrderId orderId , OrderItemId orderItemId) {
		this.orderId = orderId;
		super.setId(orderItemId);
		
	}
	
	boolean isPriceValid() {
		return price.isGreaterThanZero() && price.equals(product.getPrice()) && price.getAmount().multiply(new BigDecimal(quantity)).equals(subTotal.getAmount());
	}
	
	

}
