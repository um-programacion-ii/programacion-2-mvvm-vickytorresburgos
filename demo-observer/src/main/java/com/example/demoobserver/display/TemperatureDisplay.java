package com.example.demoobserver.display;

import com.example.demoobserver.model.WeatherData;
import com.example.demoobserver.observer.Observer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemperatureDisplay implements Observer<WeatherData> {
    private float currentTemperature;

    public TemperatureDisplay() {
        log.info("Display de temperatura creado.");
        this.currentTemperature  = 0.0f;
    }

    @Override
    public void update(WeatherData weatherData) {
        if (weatherData != null) {
            this.currentTemperature = weatherData.getTemperature();
            display();
        } else {
            log.warn("Datos meteorológicos nulos.");
        }
    }

    private void display() {
        log.info("Display de temperatura: {} oC", String.format("%.1f", this.currentTemperature));
    }

    public float getCurrentTemperature() {
        return this.currentTemperature;
    }
}