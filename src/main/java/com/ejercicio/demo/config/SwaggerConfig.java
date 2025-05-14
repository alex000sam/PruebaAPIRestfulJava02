package com.ejercicio.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful de Gestión Usuarios")
                        .description("API RESTful de Gestión Usuarios con CRUD y JWT")
                        .version("1.0.0"));
    }
}
