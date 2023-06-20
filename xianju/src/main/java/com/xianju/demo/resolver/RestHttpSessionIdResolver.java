package com.xianju.demo.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * 同时支持 sessionId 存到 cookie，header 和 request parameter
 *
 * @author songyinyin
 * @date 2020/3/18 下午 05:53
 */
@Slf4j
@Service("httpSessionIdResolver")
public class RestHttpSessionIdResolver implements HttpSessionIdResolver {

    public static final String AUTH_TOKEN = "GitsSessionID";

    private String sessionIdName = AUTH_TOKEN;

    private CookieHttpSessionIdResolver cookieHttpSessionIdResolver;

    public RestHttpSessionIdResolver() {
        initCookieHttpSessionIdResolver();
    }

    public RestHttpSessionIdResolver(String sessionIdName) {
        this.sessionIdName = sessionIdName;
        initCookieHttpSessionIdResolver();
    }

    public void initCookieHttpSessionIdResolver() {
        this.cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName(this.sessionIdName);
        this.cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer);
    }


    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {


        // request parameter
        String sessionId = request.getParameter(this.sessionIdName);
        return (sessionId != null) ? Collections.singletonList(sessionId) : Collections.emptyList();
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        log.info(AUTH_TOKEN + "={}", sessionId);
        response.setHeader(this.sessionIdName, sessionId);
        this.cookieHttpSessionIdResolver.setSessionId(request, response, sessionId);
    }

    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(this.sessionIdName, "");
        this.cookieHttpSessionIdResolver.setSessionId(request, response, "");
    }
}
