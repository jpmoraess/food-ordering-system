package br.com.food.ordering.system.payment.service.domain;

import br.com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import br.com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import br.com.food.ordering.system.payment.service.domain.entity.Payment;
import br.com.food.ordering.system.payment.service.domain.event.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {
    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages);
}
