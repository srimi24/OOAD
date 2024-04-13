package com.example.application.strategies;

import java.util.Map;

public interface BookingStrategy {
    void book(Map<String, String> bookingDetails);
}