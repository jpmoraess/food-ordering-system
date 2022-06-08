package br.com.food.ordering.system.payment.service.dataaccess.creditentry.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "credit_entry")
@Entity
public class CreditEntryEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal totalCreditAmount;
}
