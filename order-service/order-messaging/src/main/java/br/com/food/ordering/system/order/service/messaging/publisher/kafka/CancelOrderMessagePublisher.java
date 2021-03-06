package br.com.food.ordering.system.order.service.messaging.publisher.kafka;

import br.com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import br.com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import br.com.food.ordering.system.kafka.producer.service.KafkaProducer;
import br.com.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import br.com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import br.com.food.ordering.system.order.service.domain.event.publisher.DomainEventPublisher;
import br.com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CancelOrderMessagePublisher implements DomainEventPublisher<OrderCancelledEvent> {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper orderKafkaMessageHelper;

    public CancelOrderMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                       OrderServiceConfigData orderServiceConfigData,
                                       KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer,
                                       KafkaMessageHelper orderKafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.orderKafkaMessageHelper = orderKafkaMessageHelper;
    }

    @Override
    public void publish(OrderCancelledEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCancelledEvent for order id: {}", orderId);
        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
                    .orderCancelledEventToPaymentRequestAvroModel(domainEvent);

            kafkaProducer.send(
                    orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestAvroModel,
                    orderKafkaMessageHelper
                            .getKafkaCallback(
                                    orderServiceConfigData.getPaymentResponseTopicName(),
                                    paymentRequestAvroModel,
                                    orderId,
                                    "PaymentRequestAvroModel")
            );
            log.info("PaymentRequestAvroModel sent to Kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestAvroModel message to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
