package com.example.courierservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourierNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourierNotFoundException(
            CourierNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NoAvailableCourierException.class)
    public ResponseEntity<ErrorResponse> handleNoAvailableCourierException(
            NoAvailableCourierException exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidCourierStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCourierStatusException(
            InvalidCourierStatusException exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        ErrorResponse response = new ErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestBodyException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                "Invalid request body or courier status value",
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(
            Exception exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}