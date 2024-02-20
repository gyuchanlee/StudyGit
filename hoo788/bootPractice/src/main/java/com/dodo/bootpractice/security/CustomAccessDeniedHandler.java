package com.dodo.bootpractice.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 인가되지 않은 사용자 접근에 대한 로그 남기는 코드

        response.sendRedirect(request.getContextPath() + "/accessDenied");
        // 인가되지 않은 사용자를 accessDenied 엔드포인트를 통해 에러 페이지로 리다이렉트.
    }
}
