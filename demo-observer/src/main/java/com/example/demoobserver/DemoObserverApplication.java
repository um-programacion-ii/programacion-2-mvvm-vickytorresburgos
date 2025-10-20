package com.example.demoobserver;

import com.example.demoobserver.service.ObserverDemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoObserverApplication {

    public static void main(String[] args) {
        // Iniciar la aplicación Spring Boot
        ApplicationContext context = SpringApplication.run(DemoObserverApplication.class, args);

        // Obtener el servicio de demostración y ejecutar el demo
        ObserverDemoService demoService = context.getBean(ObserverDemoService.class);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎯 DEMOSTRACIÓN DEL PATRÓN OBSERVER");
        System.out.println("=".repeat(60));

        // Ejecutar la demostración completa
        demoService.runObserverDemo();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ Aplicación finalizada");
        System.out.println("=".repeat(60));

        // Cerrar la aplicación Spring Boot
        SpringApplication.exit(context);
    }

}
