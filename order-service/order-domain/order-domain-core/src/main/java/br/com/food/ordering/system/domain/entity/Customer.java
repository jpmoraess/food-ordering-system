package br.com.food.ordering.system.domain.entity;

import br.com.food.ordering.system.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {
    public Customer() {

    }

    public Customer(CustomerId customerId) {
        super.setId(customerId);
    }
}
