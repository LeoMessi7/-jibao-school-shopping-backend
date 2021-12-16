package com.t09.jibao.config;

import com.google.common.util.concurrent.RateLimiter;
import com.t09.jibao.Interceptor.TokenBucket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.concurrent.TimeUnit;

/**
 * Token bucket
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenBucket(RateLimiter.create(100, 50, TimeUnit.SECONDS)))
                .addPathPatterns("/getImageCaptcha");
    }
}