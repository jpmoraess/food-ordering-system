package br.com.food.ordering.system.order.service.domain;

import br.com.food.ordering.system.order.service.domain.entity.Order;
import br.com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import br.com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import br.com.food.ordering.system.order.service.domain.valueobject.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderSagaHelper {

    private final OrderRepository orderRepository;

    public OrderSagaHelper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findOrder(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
        if (orderOptional.isEmpty()) {
            log.error("Order with id: {} could not be found", orderId);
            throw new OrderNotFoundException("Order with id: " + orderId + " could not be found");
        }
        return orderOptional.get();
    }

    void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
