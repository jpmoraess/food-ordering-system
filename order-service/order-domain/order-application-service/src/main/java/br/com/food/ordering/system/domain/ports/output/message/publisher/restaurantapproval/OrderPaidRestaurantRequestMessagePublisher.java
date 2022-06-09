package br.com.food.ordering.system.domain.ports.output.message.publisher.restaurantapproval;

import br.com.food.ordering.system.domain.event.OrderPaidEvent;
import br.com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
