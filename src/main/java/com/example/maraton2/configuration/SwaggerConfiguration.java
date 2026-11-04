package com.example.maraton2.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Ruta Segura")
                        .version("1.0")
                        .description("Docunentacion de la API para Ruta Segura")
                        .contact(new Contact()
                                .name("Soporte API")
                                .email("d-----")));

    }

}