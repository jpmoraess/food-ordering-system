package br.com.food.ordering.system.saga;

import br.com.food.ordering.system.order.service.domain.event.DomainEvent;

public interface SagaStep<T, S extends DomainEvent, U extends DomainEvent> {
    S process(T data);

    U rollback(T data);
}
