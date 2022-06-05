package br.com.food.ordering.system.application.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(status)
                .body(ErrorDTO.builder()
                        .code(status.getReasonPhrase())
                        .message("Unexpected error")
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(ValidationException validationException) {
        var status = HttpStatus.BAD_REQUEST;
        log.error(validationException.getMessage(), validationException);
        ErrorDTO errorDTO;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            errorDTO = ErrorDTO.builder()
                    .code(status.getReasonPhrase())
                    .message(violations)
                    .build();
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            errorDTO = ErrorDTO.builder()
                    .code(status.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }
        return ResponseEntity.status(status).body(errorDTO);
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
