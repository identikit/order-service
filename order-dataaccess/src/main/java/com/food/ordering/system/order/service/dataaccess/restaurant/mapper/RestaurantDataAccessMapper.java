package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.dataaccess.restaurant.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;

@Component
public class RestaurantDataAccessMapper {
	
	public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
		//Return the list of the UUID of the products.
		return restaurant.getProducts().stream().map(x -> x.getId().getValue()).collect(Collectors.toList());
	}
	
	public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
		
		RestaurantEntity restaurantEntity = restaurantEntities.stream().findFirst().orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found!"));
		
		List<Product> restaurantProducts = restaurantEntities.stream().map( entity -> new Product(new ProductId(entity.getProductId()), entity.getProductName() , new Money(entity.getProductPrice()) )).collect(Collectors.toList());
		
		Restaurant restaurant = new Restaurant( restaurantProducts , restaurantEntity.getRestaurantActive() , new RestaurantId(restaurantEntity.getRestaurantID()) );
		
		return restaurant;
	}

}
