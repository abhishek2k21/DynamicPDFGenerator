package com.freightfox.pdfgenerator.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("pdfgenerator")
                .packagesToScan("com.freightfox.pdfgenerator.controller")  
                .pathsToMatch("/**")
                .build();
    }
}
