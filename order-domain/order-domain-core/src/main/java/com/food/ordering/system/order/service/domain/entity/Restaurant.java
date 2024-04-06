package com.food.ordering.system.order.service.domain.entity;

import java.util.List;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;

public class Restaurant extends AggregateRoot<RestaurantId> {
	
	private final List<Product> products;
	private boolean active;
	
	

	public Restaurant(List<Product> products) {
		super();
		this.products = products;
	}

	public Restaurant(List<Product> products, boolean active, RestaurantId id) {
		super();
		super.setId(id);
		this.products = products;
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<Product> getProducts() {
		return products;
	}
	
	

}
