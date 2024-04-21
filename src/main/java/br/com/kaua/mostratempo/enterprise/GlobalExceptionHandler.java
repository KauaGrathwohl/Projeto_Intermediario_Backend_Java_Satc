package br.com.kaua.mostratempo.enterprise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.security.InvalidParameterException;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleEntityNotFoundException(EntityNotFoundException e) {
        LOGGER.error("EntityNotFoundException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                404,
                e.getMessage()
        );
        return ResponseEntity.status(404).body(apiErrorMessage);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiErrorMessage> handleBusinessRuleException(BusinessRuleException e) {
        LOGGER.error("BusinessRuleException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                422,
                e.getMessage()
        );
        return ResponseEntity.status(400).body(apiErrorMessage);
    }

    // Exceções de requisições feitas através do OpenWeatherMap API
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiErrorMessage> handleHttpClientErrorException(HttpClientErrorException e) {
        LOGGER.error("HttpClientErrorException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                e.getRawStatusCode(),
                e.getMessage()
        );
        return ResponseEntity.status(e.getRawStatusCode()).body(apiErrorMessage);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ApiErrorMessage> handleHttpServerErrorException(HttpServerErrorException e) {
        LOGGER.error("HttpServerErrorException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                e.getRawStatusCode(),
                e.getMessage()
        );
        return ResponseEntity.status(e.getRawStatusCode()).body(apiErrorMessage);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiErrorMessage> handleResourceAccessException(ResourceAccessException e) {
        LOGGER.error("ResourceAccessException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                503,
                e.getMessage()
        );
        return ResponseEntity.status(503).body(apiErrorMessage);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ApiErrorMessage> handleInvalidParameterException(InvalidParameterException e) {
        LOGGER.error("InvalidParameterException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                400,
                e.getMessage()
        );
        return ResponseEntity.status(400).body(apiErrorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error("IllegalArgumentException {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                400,
                e.getMessage()
        );
        return ResponseEntity.status(400).body(apiErrorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorMessage> handleException(Exception e) {
        LOGGER.error("Exception {}", e.getMessage());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                Instant.now(),
                500,
                e.getMessage()
        );
        return ResponseEntity.status(500).body(apiErrorMessage);
    }
}
