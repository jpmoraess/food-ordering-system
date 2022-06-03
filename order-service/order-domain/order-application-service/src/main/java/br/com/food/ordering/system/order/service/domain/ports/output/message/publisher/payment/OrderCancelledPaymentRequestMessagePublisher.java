package br.com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import br.com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import br.com.food.ordering.system.order.service.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
