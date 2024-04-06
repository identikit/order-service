package com.food.ordering.system.order.service.dataaccess.order.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntityId implements Serializable{
	
	private Long id;
	private OrderEntity order;
	
	@Override
	public int hashCode() {
		return Objects.hash(id, order);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemEntityId other = (OrderItemEntityId) obj;
		return Objects.equals(id, other.id) && Objects.equals(order, other.order);
	}
	
	
	
	

}
