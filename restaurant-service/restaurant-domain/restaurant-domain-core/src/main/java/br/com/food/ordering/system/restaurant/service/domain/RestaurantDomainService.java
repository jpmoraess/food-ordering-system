package br.com.food.ordering.system.restaurant.service.domain;

import br.com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import br.com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import br.com.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import br.com.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import br.com.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages,
                                     DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
                                     DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher);
}
