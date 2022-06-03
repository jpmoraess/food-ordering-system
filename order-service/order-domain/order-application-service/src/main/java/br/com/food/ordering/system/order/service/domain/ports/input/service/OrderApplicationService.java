package br.com.food.ordering.system.order.service.domain.ports.input.service;

import br.com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import br.com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import br.com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import br.com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
