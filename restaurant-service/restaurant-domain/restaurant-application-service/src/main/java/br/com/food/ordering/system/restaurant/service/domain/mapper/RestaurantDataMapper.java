package br.com.food.ordering.system.restaurant.service.domain.mapper;

import br.com.food.ordering.system.order.service.domain.valueobject.Money;
import br.com.food.ordering.system.order.service.domain.valueobject.OrderId;
import br.com.food.ordering.system.order.service.domain.valueobject.OrderStatus;
import br.com.food.ordering.system.order.service.domain.valueobject.RestaurantId;
import br.com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import br.com.food.ordering.system.restaurant.service.domain.entity.OrderDetail;
import br.com.food.ordering.system.restaurant.service.domain.entity.Product;
import br.com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {

    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                             restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream()
                                .map(product -> Product.builder()
                                        .productId(product.getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .available(product.isAvailable())
                                        .quantity(product.getQuantity())
                                        .build()
                                ).collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }
}
