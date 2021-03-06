package br.com.food.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import br.com.food.ordering.system.order.service.domain.event.publisher.DomainEventPublisher;
import br.com.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;

public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
}
