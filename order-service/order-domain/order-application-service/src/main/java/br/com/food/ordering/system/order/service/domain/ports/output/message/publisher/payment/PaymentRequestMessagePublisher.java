package br.com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import br.com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import br.com.food.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
