package com.easyprogramming.app.logging;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uniqueId = UUID.randomUUID();
        MDC.put("requestId", uniqueId.toString());
        MDC.put("ip", servletRequest.getRemoteAddr());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
