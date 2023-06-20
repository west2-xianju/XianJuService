package com.xianju.demo.Security;

import com.xianju.demo.BaseResponse.Result;
import com.xianju.demo.Util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AnonymousAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.warn("用户需要登录，访问[{}]失败，AuthenticationException={}", request.getRequestURI(), e);

        ServletUtils.render(request, response, Result.fail(400,"用户需要登录"));
    }
}

