package com.example.courierservice.exception;

public class NoAvailableCourierException extends RuntimeException {

    public NoAvailableCourierException(String message) {
        super(message);
    }
}