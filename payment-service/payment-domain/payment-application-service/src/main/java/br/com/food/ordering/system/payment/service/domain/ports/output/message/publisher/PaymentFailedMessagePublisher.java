package br.com.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import br.com.food.ordering.system.order.service.domain.event.publisher.DomainEventPublisher;
import br.com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;

public interface PaymentFailedMessagePublisher extends DomainEventPublisher<PaymentFailedEvent> {
}
