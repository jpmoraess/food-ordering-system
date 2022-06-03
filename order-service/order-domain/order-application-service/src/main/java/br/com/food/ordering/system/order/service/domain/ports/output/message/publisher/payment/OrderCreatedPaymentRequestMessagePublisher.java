package br.com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import br.com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import br.com.food.ordering.system.order.service.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
