package com.bank.system.config;

import com.bank.system.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    LoggingInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // this interceptor will be applied to all URLs
        registry.addInterceptor(interceptor);
    }
}