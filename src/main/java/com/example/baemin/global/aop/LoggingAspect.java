package com.example.baemin.global.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    private final ObjectMapper objectMapper;

    @Pointcut("execution(* com.example.baemin..*Controller.*(..))")
    public void controller() {
    }

    @Around("controller()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getCurrentRequest();

        logRequestDetails(request, joinPoint);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;

        log.info("Time taken: {}ms", timeTaken);

        return result;
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private void logRequestDetails(HttpServletRequest request, ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Request URL: {}", getRequestURL(request));
        log.info("Parameters: [{}]", getRequestParams(request));
        log.info("Request Start At: {}", joinPoint.getSignature().toShortString());
    }

    private String getRequestParams(HttpServletRequest request) throws Throwable {
        return request.getParameterMap().entrySet().stream()
                .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                .collect(Collectors.joining(", "));
    }

    private String getRequestURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    @AfterReturning(pointcut = "controller()", returning = "returnValue")
    public void logResponse(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {
        log.info("Response End At: {}", joinPoint.getSignature().toShortString());

        if (returnValue == null) {
            log.info("Response: {}");
            return;
        }

        log.info("Response: {}", objectMapper.writeValueAsString(returnValue));
    }

    @AfterThrowing(pointcut = "controller()", throwing = "e")
    public void logError(JoinPoint joinPoint, Exception e) {
        log.error("Error occurred: {}", joinPoint.getSignature().toShortString());
        log.error("{}", e.getMessage());
    }

}
