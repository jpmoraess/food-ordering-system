package br.com.food.ordering.system.order.service.dataaccess.customer.mapper;

import br.com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;
import br.com.food.ordering.system.order.service.domain.entity.Customer;
import br.com.food.ordering.system.order.service.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()));
    }
}
