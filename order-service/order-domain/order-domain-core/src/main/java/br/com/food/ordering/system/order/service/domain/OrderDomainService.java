package br.com.food.ordering.system.order.service.domain;

import br.com.food.ordering.system.order.service.domain.entity.Order;
import br.com.food.ordering.system.order.service.domain.entity.Restaurant;
import br.com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import br.com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import br.com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
