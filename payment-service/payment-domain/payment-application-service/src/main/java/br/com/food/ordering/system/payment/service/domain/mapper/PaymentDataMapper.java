package br.com.food.ordering.system.payment.service.domain.mapper;

import br.com.food.ordering.system.order.service.domain.valueobject.CustomerId;
import br.com.food.ordering.system.order.service.domain.valueobject.Money;
import br.com.food.ordering.system.order.service.domain.valueobject.OrderId;
import br.com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import br.com.food.ordering.system.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
