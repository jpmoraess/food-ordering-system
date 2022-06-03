package br.com.food.ordering.system.order.service.domain;

import br.com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import br.com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import br.com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import br.com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import br.com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
