package com.example.demoobserver.display;

import com.example.demoobserver.model.WeatherData;
import com.example.demoobserver.observer.Observer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HumidityDisplay implements Observer<WeatherData> {
    private float currentHumidity;

    public HumidityDisplay() {
        this.currentHumidity  = 0.0f;
        log.info("Display de Humedad creado.");
    }

    @Override
    public void update(WeatherData weatherData) {
        if (weatherData != null) {
            this.currentHumidity = weatherData.getHumidity();
            display();
        } else {
            log.warn("Datos meteorológicos nulos.");
        }
    }

    public void display() {
        log.info("Display de humedad: {} %", String.format("%.1f", this.currentHumidity));
    }

    public float getCurrentHumidity() {
        return this.currentHumidity;
    }
}