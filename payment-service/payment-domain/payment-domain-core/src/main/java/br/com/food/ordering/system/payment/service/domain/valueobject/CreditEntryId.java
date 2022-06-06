package br.com.food.ordering.system.payment.service.domain.valueobject;

import br.com.food.ordering.system.order.service.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
