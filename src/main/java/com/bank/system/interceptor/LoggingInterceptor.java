package com.bank.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("Executing Before Handler method...");
        logger.debug("request info: " + getRequestDetails(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("Executing After Handler method...");
        logger.debug("response info: " + getResponseDetails(response));
    }


    private StringBuilder getRequestDetails(HttpServletRequest request) {
        StringBuilder details = new StringBuilder();
        details.append("Request Method: ").append(request.getMethod()).append("\n");
        details.append("Request URL: ").append(request.getRequestURL()).append("\n");
        details.append("Query String: ").append(request.getQueryString()).append("\n");
        details.append("Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            details.append("  ").append(headerName).append(": ").append(headerValue).append("\n");
        }
        details.append("Parameters:\n");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            details.append("  ").append(paramName).append(": ");
            for (String value : paramValues) {
                details.append(value).append(", ");
            }
            details.append("\n");
        }
        return details;
    }


    private String getResponseDetails(HttpServletResponse response) {
        StringBuilder details = new StringBuilder();
        int status = response.getStatus();
        details.append("Response Status: ").append(status).append(" ").append("<br>");
        details.append("Response Headers:<br>");
        response.getHeaderNames().forEach(headerName -> {
            String headerValue = response.getHeader(headerName);
            details.append("  ").append(headerName).append(": ").append(headerValue).append("<br>");
        });
        return details.toString();
    }

}
