package com.food.ordering.system.order.service.dataaccess.restaurant;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_restaurant_m_view" , schema = "restaurant")
@Getter
@Setter
@Builder
@IdClass(RestaurantEntityId.class)
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
	
	@Id
	private UUID restaurantID;
	@Id
	private UUID productId;
	private String restaurantName;
	private Boolean restaurantActive;
	private String productName;
	private BigDecimal productPrice;
	
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
		RestaurantEntity other = (RestaurantEntity) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(restaurantID, other.restaurantID);
	}
	
	

}
