package com.amyurov.smartphoneecom.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        var id = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        long startTime = System.currentTimeMillis();

        log.info("Request: {} {}; id:{}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                id);

        httpRequest.setAttribute("id", id);

        filterChain.doFilter(httpRequest, httpResponse);

        long duration = System.currentTimeMillis() - startTime;

        log.info("Response: status:{}, duration:{}ms, for:{}",
                httpResponse.getStatus(),
                duration, httpRequest.getAttribute("id"));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
