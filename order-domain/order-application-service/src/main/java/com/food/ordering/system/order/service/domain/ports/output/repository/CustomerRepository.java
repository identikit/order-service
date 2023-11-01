package com.food.ordering.system.order.service.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

import com.food.ordering.system.order.service.domain.entity.Customer;

//Will be implemented by the dataaccess layer
public interface CustomerRepository {
	
	Optional<Customer> findCustomer(UUID customerID);

}
