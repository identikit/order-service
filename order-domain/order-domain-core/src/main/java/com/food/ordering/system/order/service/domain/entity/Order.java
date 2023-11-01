package com.food.ordering.system.order.service.domain.entity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

public class Order extends AggregateRoot<OrderId> {

	private final CustomerId customerId;
	private final RestaurantId restaurantId;
	private final StreetAddress deliveryAdrress;
	private final Money price;
	private final List<OrderItem> items;

	private TrackingId trackingId;
	private OrderStatus orderStatus;
	private List<String> failureMessages;



	public Order(CustomerId customerId, RestaurantId restaurantId, StreetAddress deliveryAdrress, Money price,
			List<OrderItem> items, TrackingId trackingId, OrderStatus orderStatus, List<String> failureMessages, OrderId orderId) {

		super.setId(orderId);
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.deliveryAdrress = deliveryAdrress;
		this.price = price;
		this.items = items;
		this.trackingId = trackingId;
		this.orderStatus = orderStatus;
		this.failureMessages = failureMessages;
	}

	/*Questo è il metodo utilizzato per inizializzare l'ordine*/
	public void initializeOrder() {
		super.setId( new OrderId( UUID.randomUUID() ) );
		this.trackingId = new TrackingId( UUID.randomUUID() );
		this.orderStatus = OrderStatus.PENDING;
		this.initializeOrderItems();
	}

	/*Metodo utilizzato per validare l'ordine*/
	public void validateOrder() {
		validateInitialOrder();
		validateTotalPrice();
		validateItemsPrice();
	}

	private void initializeOrderItems() {

		long itemId = 1;
		for(OrderItem orderItem : items) {

			orderItem.initializeOrderItem( super.getId() , new OrderItemId(itemId++));

		}



	}

	public void pay() {
		if(this.orderStatus != OrderStatus.PENDING) {
			throw new OrderDomainException("Order is not in correct state for pay operation!");
		}

		this.orderStatus = OrderStatus.PAID;
	}

	public void approve() {

		if(this.orderStatus != OrderStatus.PAID) {
			throw new OrderDomainException("Order is not in correct state for approve operation!");
		}

		this.orderStatus = OrderStatus.APPROVED;
	}

	public void initCancel(List<String> failuremessages) {

		if(this.orderStatus != OrderStatus.PAID) {
			throw new OrderDomainException("Order is not in correct state for initCancel operation!");
		}

		this.orderStatus = OrderStatus.CANCELLING;
		updateFailureMessages(failuremessages);
	}

	private void updateFailureMessages(List<String> failureMessages) {
		
		if( CollectionUtils.isNotEmpty( this.failureMessages ) && CollectionUtils.isNotEmpty( failureMessages )) {
			this.failureMessages.addAll( failureMessages.stream().filter( message -> !message.isEmpty() ).collect(Collectors.toList()) );
		}
		
		if( CollectionUtils.isEmpty( this.failureMessages ) ) {
			this.failureMessages = failureMessages;
		}
		
	}

	public void cancel( List<String> failuremessages ) {

		if( !( this.orderStatus == OrderStatus.CANCELLING || this.orderStatus == OrderStatus.PENDING ) ) {
			throw new OrderDomainException("Order is not in correct state for cancel operation!");
		}

		this.orderStatus = OrderStatus.CANCELLED;
		updateFailureMessages(failuremessages);
	}


	private void validateItemsPrice() {

		Money orderItemsTotal = items.stream().map( orderItem -> {
			validateItemPrice(orderItem);
			return orderItem.getSubTotal();
		}).reduce( Money.ZERO , Money::add);

		if(!this.price.equals(orderItemsTotal)) {
			throw new OrderDomainException(String.format("Total price: %s is not equals to Order item total: %s !", price.getAmount() , orderItemsTotal.getAmount()));
		}

	}

	private void validateItemPrice(OrderItem orderItem) {
		if(!orderItem.isPriceValid()) {
			throw new OrderDomainException(String.format("Order item price: %s is not valid for product %s",orderItem.getPrice().getAmount() , orderItem.getProduct().getId().getValue()));
		}

	}
	private void validateTotalPrice() {
		if(price == null || price.isGreaterThanZero()) {
			throw new OrderDomainException("Total price must be greater then zero!");
		}

	}

	private void validateInitialOrder() {
		if(orderStatus != null || super.getId() != null) {
			throw new OrderDomainException("Order is not in correct state for initialization!");
		}

	}

	public TrackingId getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(TrackingId trackingId) {
		this.trackingId = trackingId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<String> getFailureMessages() {
		return failureMessages;
	}
	public void setFailureMessages(List<String> failureMessages) {
		this.failureMessages = failureMessages;
	}
	public CustomerId getCustomerId() {
		return customerId;
	}
	public RestaurantId getRestaurantId() {
		return restaurantId;
	}
	public StreetAddress getDeliveryAdrress() {
		return deliveryAdrress;
	}
	public Money getPrice() {
		return price;
	}
	public List<OrderItem> getItems() {
		return items;
	}





}
