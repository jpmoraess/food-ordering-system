package br.com.food.ordering.system.order.service.domain.entity;

import br.com.food.ordering.system.order.service.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {

    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
