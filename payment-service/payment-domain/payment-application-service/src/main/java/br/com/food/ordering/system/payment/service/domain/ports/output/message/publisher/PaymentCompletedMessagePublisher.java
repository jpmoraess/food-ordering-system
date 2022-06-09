package br.com.food.ordering.system.payment.service.domain.ports.output.message.publisher;

import br.com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import br.com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;

public interface PaymentCompletedMessagePublisher extends DomainEventPublisher<PaymentCompletedEvent> {
}
