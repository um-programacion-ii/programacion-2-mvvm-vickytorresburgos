package com.example.demoobserver.service;

import com.example.demoobserver.display.HumidityDisplay;
import com.example.demoobserver.display.TemperatureDisplay;
import com.example.demoobserver.subject.implementation.WeatherStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ObserverDemoService {
    public void runObserverDemo() {
        /**
         * Ejecuta la demostración completa del patrón Observer.
         * Simula un escenario real donde una estación meteorológica
         * actualiza diferentes displays cuando cambian las condiciones climáticas.
         */
        log.info("🚀 Iniciando demostración del patrón Observer");
        log.info("==============================================");

        // 1. Crear la estación meteorológica (el Subject)
        WeatherStation weatherStation = new WeatherStation();
        log.info("📡 Estación meteorológica creada");

        // 2. Crear los displays (los Observers)
        TemperatureDisplay tempDisplay = new TemperatureDisplay();
        HumidityDisplay humidityDisplay = new HumidityDisplay();
        log.info("📺 Displays creados");

        // 3. Registrar los observadores con el sujeto
        log.info("🔗 Registrando observadores...");
        weatherStation.registerObserver(tempDisplay);
        weatherStation.registerObserver(humidityDisplay);
        log.info("✅ Observadores registrados exitosamente");

        // 4. Simular cambios climáticos - Primera actualización
        log.info("\n🌤️  Primera actualización climática:");
        weatherStation.setMeasurements(25.5f, 65.0f, 1013.2f);

        // 5. Simular más cambios climáticos
        log.info("\n🌧️  Segunda actualización climática:");
        weatherStation.setMeasurements(22.1f, 78.5f, 1009.8f);

        // 6. Simular cambio solo de temperatura y humedad
        log.info("\n☀️  Tercera actualización climática:");
        weatherStation.setMeasurements(28.7f, 45.2f);

        // 7. Demostrar remoción de un observador
        log.info("\n❌ Removiendo display de temperatura...");
        weatherStation.removeObserver(tempDisplay);

        // 8. Última actualización - solo el display de humedad debería actualizarse
        log.info("\n❄️  Cuarta actualización climática (solo humedad):");
        weatherStation.setMeasurements(18.3f, 82.1f, 1015.6f);

        // 9. Mostrar estadísticas finales
        log.info("\n📊 Estadísticas finales:");
        log.info("   Observadores registrados: {}", weatherStation.getObservers().size());
        log.info("   Última temperatura en display: {}°C", tempDisplay.getCurrentTemperature());
        log.info("   Última humedad en display: {}%", humidityDisplay.getCurrentHumidity());

        log.info("\n✅ Demostración del patrón Observer completada");
        log.info("==============================================");
    }

    /**
     * Demostración simplificada que muestra solo los conceptos básicos.
     * Útil para entender rápidamente cómo funciona el patrón.
     */
    public void runBasicDemo() {
        log.info("🎯 Demo básica del patrón Observer");
        log.info("=================================");

        // Crear componentes básicos
        WeatherStation station = new WeatherStation();
        TemperatureDisplay display = new TemperatureDisplay();

        // Registrar y probar
        station.registerObserver(display);
        station.setMeasurements(20.0f, 50.0f);

        log.info("✅ Demo básica completada");
    }
}