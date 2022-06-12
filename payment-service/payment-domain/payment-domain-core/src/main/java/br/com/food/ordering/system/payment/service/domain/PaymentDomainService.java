package br.com.food.ordering.system.payment.service.domain;

import br.com.food.ordering.system.order.service.domain.event.publisher.DomainEventPublisher;
import br.com.food.ordering.system.payment.service.domain.entity.CreditEntry;
import br.com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import br.com.food.ordering.system.payment.service.domain.entity.Payment;
import br.com.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import br.com.food.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import br.com.food.ordering.system.payment.service.domain.event.PaymentEvent;
import br.com.food.ordering.system.payment.service.domain.event.PaymentFailedEvent;

import java.util.List;

public interface PaymentDomainService {
    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages,
                                            DomainEventPublisher<PaymentCompletedEvent>
                                                    paymentCompletedEventDomainEventPublisher,
                                            DomainEventPublisher<PaymentFailedEvent>
                                                    paymentFailedEventDomainEventPublisher);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages,
                                          DomainEventPublisher<PaymentCancelledEvent>
                                                  paymentCancelledEventDomainEventPublisher,
                                          DomainEventPublisher<PaymentFailedEvent>
                                                  paymentFailedEventDomainEventPublisher);
}
