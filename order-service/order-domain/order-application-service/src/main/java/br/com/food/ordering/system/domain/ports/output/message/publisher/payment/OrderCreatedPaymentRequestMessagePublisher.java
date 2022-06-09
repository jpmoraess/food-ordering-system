package br.com.food.ordering.system.domain.ports.output.message.publisher.payment;

import br.com.food.ordering.system.domain.event.OrderCreatedEvent;
import br.com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
