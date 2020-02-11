package com.danbai.dblogin.config;

import com.danbai.dblogin.Interceptor.PassManagementInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DanBai
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    PassManagementInterceptor pa;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pa);
    }
}
