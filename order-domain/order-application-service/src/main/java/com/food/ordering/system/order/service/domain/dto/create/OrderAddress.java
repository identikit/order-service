package com.food.ordering.system.order.service.domain.dto.create;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class OrderAddress {
	
	@NotNull
	@Max(value = 50)
	private final String street;
	@NotNull
	@Max(value = 10)
	private final String postalCode;
	@NotNull
	@Max(value = 50)
	private final String city;
	
	public OrderAddress(@NotNull @Max(50) String street, @NotNull @Max(10) String postalCode,
			@NotNull @Max(50) String city) {
		super();
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getCity() {
		return city;
	}
	
	

}
