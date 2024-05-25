package com.amyurov.smartphoneecom.advice;

import com.amyurov.smartphoneecom.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String VALIDATION_ERROR_MESSAGE = "Validation failed";
    private static final String INTERNAL_ERROR_MESSAGE = "Error executing a database query";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }

        log.error("Error: {}, trace:{}", ex.getMessage(), ex.getStackTrace());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(),
                VALIDATION_ERROR_MESSAGE, errors);
        return handleExceptionInternal(
                ex, apiErrorResponse, headers, HttpStatus.resolve(apiErrorResponse.getStatus()), request);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleRuntimeException(SQLException ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                INTERNAL_ERROR_MESSAGE, List.of(ex.getMessage()));
        log.error("Error: {}, trace:{}", ex.getMessage(), ex.getStackTrace());
        return ResponseEntity.internalServerError().body(apiErrorResponse);
    }
}
