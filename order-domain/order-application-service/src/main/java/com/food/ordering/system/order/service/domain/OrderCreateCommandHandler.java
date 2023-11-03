package com.food.ordering.system.order.service.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCreateCommandHandler {
	
	private final OrderCreateHelper orderCreateHelper;
	
	private final OrderDataMapper orderDataMapper;
	
	private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;
	
	
	public OrderCreateCommandHandler(OrderCreateHelper orderCreateHelper, OrderDataMapper orderDataMapper, OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
		super();
		this.orderCreateHelper = orderCreateHelper;
		this.orderDataMapper = orderDataMapper;
		this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
	}



	public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
		
		//Prima creiamo l'ordine e lo salviamo a db.
		OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
		log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
		this.orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
		return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
		
	}

}
