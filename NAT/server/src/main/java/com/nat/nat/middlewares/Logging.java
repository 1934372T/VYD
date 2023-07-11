package com.nat.nat.middlewares;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Logging implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  httpRequest  = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        logger.info("Request URL: " + httpRequest.getRequestURL());

        chain.doFilter(request, response);

        logger.info("Response Status: " + httpResponse.getStatus());
    }
}
