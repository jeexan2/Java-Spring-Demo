package com.sit.jbc;

import com.sit.jbc.service.security.impl.CustomAuthorizationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class CoisduJbcApplication/* extends SpringBootServletInitializer*/{

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(CoisduJbcApplication.class);
//    }

//	@Bean
//	public CustomAuthorizationInterceptor customInterceptor() {
//		CustomAuthorizationInterceptor interceptor = new CustomAuthorizationInterceptor();
//		return interceptor;
//	}



	public static void main(String[] args) {

	    SpringApplication.run(CoisduJbcApplication.class, args);
	}
}
