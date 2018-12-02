package com.sit.jbc.auth;

import com.sit.jbc.service.security.impl.CustomAuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CustomAuthorizationInterceptor customInterceptor() {
        CustomAuthorizationInterceptor interceptor = new CustomAuthorizationInterceptor();
        return interceptor;
    }
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor() );
    }
}