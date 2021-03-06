package br.com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import br.com.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import br.com.food.ordering.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import br.com.food.ordering.system.order.service.domain.entity.Product;
import br.com.food.ordering.system.order.service.domain.entity.Restaurant;
import br.com.food.ordering.system.order.service.domain.valueobject.Money;
import br.com.food.ordering.system.order.service.domain.valueobject.ProductId;
import br.com.food.ordering.system.order.service.domain.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity = restaurantEntities
                .stream().findFirst().orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found"));

        List<Product> restaurantProducts = restaurantEntities.stream()
                .map(entity ->
                        new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                                new Money(entity.getProductPrice()))).toList();

        return Restaurant.builder()
                .restauranteId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }
}
