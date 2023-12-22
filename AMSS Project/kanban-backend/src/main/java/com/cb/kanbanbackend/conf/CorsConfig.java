package com.cb.kanbanbackend.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Die Klasse `CorsConfig` dient zur Konfiguration der CORS-Einstellungen der Anwendung.
@Configuration
@EnableWebMvc
public class CorsConfig {

    // Hier wird der Wert der erlaubten Quellen aus der Konfiguration gelesen.
    @Value("${service.allowedOrigins}")
    private String allowedOrigins;

    @Autowired
    Environment environment;

    // Dieses Bean konfiguriert die CORS-Einstellungen der Anwendung.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            // In dieser Methode werden die CORS-Einstellungen der Anwendung konfiguriert.
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Dies ermöglicht CORS-Anfragen auf alle Pfade in der Anwendung.
                        .allowedOrigins(allowedOrigins) // Hier wird festgelegt, welche Quellen erlaubt sind, Anfragen an die Anwendung zu senden.
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Hier wird festgelegt, welche HTTP-Methoden von den erlaubten Quellen verwendet werden dürfen.
                        .allowedHeaders("*") // Hier wird festgelegt, dass alle Header in den Anfragen erlaubt sind.
                        .allowCredentials(true); // Dies ermöglicht es den erlaubten Quellen, Anfragen mit Anmeldeinformationen (Cookies, HTTP-Authentifizierung) zu senden.
            }
        };
    }
}