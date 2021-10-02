package com.bank.reckoning.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration options of Swagger.
 */
@ConfigurationProperties("swagger")
@Data
public class SwaggerConfigProperties {

    private String host;
}