package com.example.demoobserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = 1013.25f;
    }
}