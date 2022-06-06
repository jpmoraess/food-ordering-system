package br.com.food.ordering.system.order.service.dataaccess.order.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_address")
@EqualsAndHashCode(of = "id")
@Entity
public class OrderAddressEntity {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private String street;
    private String postalCode;
    private String city;
}
