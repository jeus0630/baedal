package com.example.baemin.global.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.Map;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map inputMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        log.info("요청 URL: {}", request.getRequestURI());
        log.info("요청 Parameter: {}", request.getQueryString());
        log.info("요청 Body: {}", inputMap);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ContentCachingResponseWrapper responseWrapper = getResponseWrapper(response);
        String responseBody = getResponseBody(responseWrapper);
        log.info("응답 Status: {}", response.getStatus());
        log.info("응답 Body: {}", responseBody);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    private String getResponseBody(ContentCachingResponseWrapper responseWrapper) {
        return new String(responseWrapper.getContentAsByteArray());
    }

    private ContentCachingResponseWrapper getResponseWrapper(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        }
        return new ContentCachingResponseWrapper(response);
    }

}
