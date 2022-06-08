package br.com.food.ordering.system.payment.service.dataaccess.credithistory.entity;

import br.com.food.ordering.system.payment.service.domain.valueobject.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "credit_history")
@Entity
public class CreditHistoryEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
