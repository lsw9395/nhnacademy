package com.nhnacademy.exam.intercepter;

import com.nhnacademy.exam.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final String AUTH_USER_ID = "nhnacademy";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-USER-ID");
        // X-USER-ID가 비어 있는 경우
        if (userId == null || userId.isEmpty()) {
            throw new UnauthorizedException();
        }
        if(userId.equals(AUTH_USER_ID)){
            return true;
        }
        throw new UnauthorizedException();
    }
}
