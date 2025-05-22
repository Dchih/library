package com.chih.library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("图书馆管理系统API")
                        .description("图书馆管理系统的所有REST API")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("开发者")
                                .email("example@example.com")));
    }
}