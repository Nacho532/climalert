package com.utn.climalert.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherRecord {
    private Long id;
    private double temperature;
    private int humidity;
    private LocalDateTime timestamp;

    public WeatherRecord(double temperature, int humidity, LocalDateTime timestamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

}
