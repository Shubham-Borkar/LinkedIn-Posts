package com.javad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Generated;


@Generated
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@Configuration
public class SwaggerConfig {

    /**
     * {@link OpenAPI} bean to generate swagger spec
     * 
     * @return
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Shubham REST APIs (Public)").version("v2")
        		
        		.version("1.0.0"));
    }
    

}