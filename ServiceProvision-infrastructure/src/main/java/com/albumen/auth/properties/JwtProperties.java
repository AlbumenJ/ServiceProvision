package com.albumen.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Albumen
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * jwt 签发key
     */
    private String jwtKey = "security";

    /**
     * jwt 签发人
     */
    private String jwtIssuer = "Albumen";
}
