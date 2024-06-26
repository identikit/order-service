package com.food.ordering.system.order.service.dataaccess.restaurant;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

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
public class RestaurantEntityId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3369319806483706119L;
	
	private UUID restaurantID;
	private UUID productId;
	@Override
	public int hashCode() {
		return Objects.hash(productId, restaurantID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantEntityId other = (RestaurantEntityId) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(restaurantID, other.restaurantID);
	}
	
	

}
