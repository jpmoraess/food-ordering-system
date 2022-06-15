package br.com.food.ordering.system.order.service.domain;

import br.com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import br.com.food.ordering.system.order.service.domain.entity.Order;
import br.com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import br.com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import br.com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import br.com.food.ordering.system.order.service.domain.outbox.scheduler.approval.ApprovalOutboxHelper;
import br.com.food.ordering.system.order.service.domain.outbox.scheduler.payment.PaymentOutboxHelper;
import br.com.food.ordering.system.order.service.domain.valueobject.OrderStatus;
import br.com.food.ordering.system.outbox.OutboxStatus;
import br.com.food.ordering.system.saga.SagaStatus;
import br.com.food.ordering.system.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.food.ordering.system.order.service.domain.DomainConstants.UTC;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse> {

    private final OrderDomainService orderDomainService;
    private final OrderSagaHelper orderSagaHelper;
    private final PaymentOutboxHelper paymentOutboxHelper;
    private final ApprovalOutboxHelper approvalOutboxHelper;
    private final OrderDataMapper orderDataMapper;

    public OrderPaymentSaga(OrderDomainService orderDomainService,
                            OrderSagaHelper orderSagaHelper,
                            PaymentOutboxHelper paymentOutboxHelper,
                            ApprovalOutboxHelper approvalOutboxHelper,
                            OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderSagaHelper = orderSagaHelper;
        this.paymentOutboxHelper = paymentOutboxHelper;
        this.approvalOutboxHelper = approvalOutboxHelper;
        this.orderDataMapper = orderDataMapper;
    }

    @Override
    @Transactional
    public void process(PaymentResponse paymentResponse) {
        Optional<OrderPaymentOutboxMessage> orderPaymentOutboxMessageResponse = paymentOutboxHelper
                .getPaymentOutboxMessageBySagaIdAndStatus(UUID.fromString(paymentResponse.getSagaId()), SagaStatus.STARTED);

        if (orderPaymentOutboxMessageResponse.isEmpty()) {
            log.info("An outbox message with saga id: {} is already processed", paymentResponse.getSagaId());
            return;
        }

        OrderPaymentOutboxMessage orderPaymentOutboxMessage = orderPaymentOutboxMessageResponse.get();

        log.info("Completing payment for order with id: {}", paymentResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(paymentResponse.getOrderId());
        OrderPaidEvent orderPaidEvent = orderDomainService.payOrder(order);
        orderSagaHelper.saveOrder(order);

        SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(orderPaidEvent.getOrder().getOrderStatus());

        paymentOutboxHelper.save(getUpdatedPaymentOutboxMessage(
                orderPaymentOutboxMessage,
                orderPaidEvent.getOrder().getOrderStatus(),
                sagaStatus));

        approvalOutboxHelper.saveApprovalOutboxMessage(
                orderDataMapper.orderPaidEventToOrderApprovalEventPayload(orderPaidEvent),
                orderPaidEvent.getOrder().getOrderStatus(),
                sagaStatus,
                OutboxStatus.STARTED,
                UUID.fromString(paymentResponse.getSagaId()));

        log.info("Order with id: {} is paid", order.getId().getValue());
    }

    @Override
    @Transactional
    public void rollback(PaymentResponse paymentResponse) {
        log.info("Cancelling order with id: {}", paymentResponse.getOrderId());
        Order order = orderSagaHelper.findOrder(paymentResponse.getOrderId());
        orderDomainService.cancelOrder(order, paymentResponse.getFailureMessages());
        orderSagaHelper.saveOrder(order);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private OrderPaymentOutboxMessage getUpdatedPaymentOutboxMessage(
            OrderPaymentOutboxMessage orderPaymentOutboxMessage,
            OrderStatus orderStatus,
            SagaStatus sagaStatus) {
        orderPaymentOutboxMessage.setProcessedAt(ZonedDateTime.now(ZoneId.of(UTC)));
        orderPaymentOutboxMessage.setOrderStatus(orderStatus);
        orderPaymentOutboxMessage.setSagaStatus(sagaStatus);
        return orderPaymentOutboxMessage;
    }
}
