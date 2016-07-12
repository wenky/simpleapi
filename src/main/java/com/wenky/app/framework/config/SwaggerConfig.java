package com.wenky.app.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;

@Configuration
@Import({SpringSwaggerConfig.class})
public class SwaggerConfig {
}