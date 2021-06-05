package com.albumen.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Albumen
 */
@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 鉴权过期时间（单位：毫秒）
     */
    private Long expire = 7200000L;

    /**
     * 启用Session存储Token
     */
    private boolean enableSession = false;

}
