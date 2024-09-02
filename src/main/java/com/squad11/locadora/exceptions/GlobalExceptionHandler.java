package com.squad11.locadora.exceptions;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonAlreadyActiveException.class)
    public ResponseEntity<RestErrorResponse> handlePersonAlreadyActiveException(PersonAlreadyActiveException e) {
        int statusCode = HttpStatus.CONFLICT.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(RentalAlreadyPaidException.class)
    public ResponseEntity<RestErrorResponse> handleRentalAlreadyPaidException(RentalAlreadyPaidException e) {
        int statusCode = HttpStatus.CONFLICT.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<RestErrorResponse> handleEmptyCartException(EmptyCartException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }



    @ExceptionHandler(FieldAlreadyInUseException.class)
    public ResponseEntity<RestErrorResponse> handleFieldAlreadyInUseException(FieldAlreadyInUseException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(UnconfirmedRegistrationException.class)
    public ResponseEntity<RestErrorResponse> handleUnconfirmedRegistrationException(UnconfirmedRegistrationException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }


    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<RestErrorResponse> handleTokenExpiredException(TokenExpiredException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorResponse> handleEntityNotFound(EntityNotFoundException e) {
        int statusCode = HttpStatus.NOT_FOUND.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<RestErrorResponseWithErrors> HandlerMethodValidationException(HandlerMethodValidationException e) {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        List<ApiError> errors = new ArrayList<>();

        e.getAllValidationResults().forEach(validator -> {
            String parameterName = validator.getMethodParameter().getParameterName();

            validator.getResolvableErrors().forEach(ve -> {
                errors.add(new ApiError(parameterName, ve.getDefaultMessage()));
            });
        });


        RestErrorResponseWithErrors error = new RestErrorResponseWithErrors(
                statusCode,
                errors,
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorResponseWithErrors>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("ok");
        int statusCode = HttpStatus.BAD_REQUEST.value();

        List<ApiError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(ex -> {
                    String pointer = ex.getField();

                    int t = Objects.requireNonNull(ex.getDefaultMessage()).indexOf(";");

                    String reason = t != -1
                            ? ex.getDefaultMessage().substring(t + 1).trim()
                            : ex.getDefaultMessage();

                    return new ApiError(pointer, reason);

                })
                .toList();
        RestErrorResponseWithErrors error = new RestErrorResponseWithErrors(
                statusCode,
                errors,
                LocalDateTime.now()
        );


        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) throws IOException {
        int statusCode = HttpStatus.BAD_REQUEST.value();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                "Required request body is missing",
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorResponse> handleException(Exception e) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        e.printStackTrace();

        RestErrorResponse error = new RestErrorResponse(
                statusCode,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

}

