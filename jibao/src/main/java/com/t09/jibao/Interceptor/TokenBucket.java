package com.t09.jibao.Interceptor;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import com.google.common.util.concurrent.RateLimiter;

public class TokenBucket implements HandlerInterceptor {

    private final RateLimiter tokenBucket;

    public TokenBucket(RateLimiter tokenBucket) {
        super();
        this.tokenBucket = tokenBucket;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(this.tokenBucket.tryAcquire()) {
            return true;
        }
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.getWriter().write("busy!");
        return false;
    }
}
