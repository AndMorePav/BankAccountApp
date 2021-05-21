package com.bank.reckoning.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration options of Swagger.
 */
@ConfigurationProperties("swagger")
@Getter
@Setter
public class SwaggerConfigProperties {

    private String host;
}