package com.albumen.auth.config;


import com.albumen.auth.filter.CustomAuthenticationFilter;
import com.albumen.auth.handler.CustomAccessDeniedHandler;
import com.albumen.auth.handler.CustomHttp401AuthenticationEntryPoint;
import com.albumen.auth.properties.SecurityProperties;
import com.albumen.auth.source.IDataStore;
import com.albumen.auth.source.IPermissionPath;
import com.albumen.auth.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Albumen
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomHttp401AuthenticationEntryPoint customHttp401AuthenticationEntryPoint;
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    private JwtUtil jwtUtil;
    private IDataStore dataStore;
    private IPermissionPath permissionPath;
    private SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        if(permissionPath.ignorePath() != null){
            permissionPath.ignorePath().forEach(ignoring::antMatchers);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager(), dataStore, jwtUtil, securityProperties))
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customHttp401AuthenticationEntryPoint);

        http
                .headers().xssProtection().xssProtectionEnabled(true);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
        permissionPath.permitPath().forEach(path -> authorizeRequests.antMatchers(path).permitAll());
        permissionPath.authenticatedPath().forEach(path -> authorizeRequests.antMatchers(path).authenticated());

    }
}
