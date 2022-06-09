package br.com.food.ordering.system.domain.ports.input.service;

import br.com.food.ordering.system.domain.dto.create.CreateOrderCommand;
import br.com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import br.com.food.ordering.system.domain.dto.track.TrackOrderQuery;
import br.com.food.ordering.system.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
