package br.com.food.ordering.system.payment.service.domain.ports.output.repository;

import br.com.food.ordering.system.order.service.domain.valueobject.CustomerId;
import br.com.food.ordering.system.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save(CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId(CustomerId customerId);
}
