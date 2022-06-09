package br.com.food.ordering.system.domain.ports.output.message.publisher.payment;

import br.com.food.ordering.system.domain.event.OrderCancelledEvent;
import br.com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
