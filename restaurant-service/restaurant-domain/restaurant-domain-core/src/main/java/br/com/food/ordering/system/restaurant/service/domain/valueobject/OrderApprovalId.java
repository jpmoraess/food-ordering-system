package br.com.food.ordering.system.restaurant.service.domain.valueobject;

import br.com.food.ordering.system.order.service.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
