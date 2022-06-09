package br.com.food.ordering.system.service.application.exception.handler;

import br.com.food.ordering.system.application.handler.ErrorDTO;
import br.com.food.ordering.system.application.handler.GlobalExceptionHandler;
import br.com.food.ordering.system.domain.exception.OrderDomainException;
import br.com.food.ordering.system.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(OrderDomainException.class)
    public ResponseEntity<ErrorDTO> handleException(OrderDomainException orderDomainException) {
        log.error(orderDomainException.getMessage(), orderDomainException);
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(ErrorDTO.builder()
                        .code(status.getReasonPhrase())
                        .message(orderDomainException.getMessage())
                        .build());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleException(OrderNotFoundException orderNotFoundException) {
        log.error(orderNotFoundException.getMessage(), orderNotFoundException);
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(ErrorDTO.builder()
                        .code(status.getReasonPhrase())
                        .message(orderNotFoundException.getMessage())
                        .build());
    }
}
