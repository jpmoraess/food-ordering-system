package br.com.food.ordering.system.order.service.domain.event.publisher;

import br.com.food.ordering.system.order.service.domain.event.DomainEvent;

public interface DomainEventPublisher<T> extends DomainEvent<T> {

    void publish(T domainEvent);
}
