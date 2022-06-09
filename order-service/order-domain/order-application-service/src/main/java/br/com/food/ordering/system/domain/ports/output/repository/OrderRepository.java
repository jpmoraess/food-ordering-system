package br.com.food.ordering.system.domain.ports.output.repository;

import br.com.food.ordering.system.domain.entity.Order;
import br.com.food.ordering.system.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
