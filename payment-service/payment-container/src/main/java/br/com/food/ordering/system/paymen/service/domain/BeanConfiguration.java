package br.com.food.ordering.system.paymen.service.domain;

import br.com.food.ordering.system.payment.service.domain.PaymentDomainService;
import br.com.food.ordering.system.payment.service.domain.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
