package com.food.ordering.system.order.service.dataaccess.restaurant.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.order.service.dataaccess.restaurant.RestaurantEntity;
import com.food.ordering.system.order.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.restaurant.repository.RestaurantJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository{
	
	
	private final RestaurantDataAccessMapper restaurantDataAccessMaper;
	private final RestaurantJpaRepository restaurantJpaRepository;
	
	

	public RestaurantRepositoryImpl(RestaurantDataAccessMapper restaurantDataAccessMaper,
			RestaurantJpaRepository restaurantJpaRepository) {
		super();
		this.restaurantDataAccessMaper = restaurantDataAccessMaper;
		this.restaurantJpaRepository = restaurantJpaRepository;
	}



	@Override
	public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
		
		List<UUID> restaurantProducts = restaurantDataAccessMaper.restaurantToRestaurantProducts(restaurant);
		
		
		Optional<List<RestaurantEntity>> restaurantEntities = restaurantJpaRepository.findByRestaurantIdAndProductIdIn( restaurant.getId().getValue() , restaurantProducts );
		
		if( restaurantEntities.isPresent() ) {
			
			return Optional.of( restaurantDataAccessMaper.restaurantEntityToRestaurant( restaurantEntities.get() ) );
		} else {
			return Optional.empty();
		}
		
		
	}

}
