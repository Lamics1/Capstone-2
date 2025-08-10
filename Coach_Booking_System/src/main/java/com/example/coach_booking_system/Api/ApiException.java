package com.example.coach_booking_system.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
