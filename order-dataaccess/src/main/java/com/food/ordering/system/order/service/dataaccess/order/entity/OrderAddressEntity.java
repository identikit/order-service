package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddressEntity {
	
	@Id
	private UUID id;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private OrderEntity order;
	
	private String street;
	private String postalCode;
	private String city;
	
	@Override
	public int hashCode() {
		return Objects.hash(city, id, order, postalCode, street);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderAddressEntity other = (OrderAddressEntity) obj;
		return Objects.equals(city, other.city) && Objects.equals(id, other.id) && Objects.equals(order, other.order)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(street, other.street);
	}
	
	

}
