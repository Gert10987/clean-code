package com.easyprogramming.app.logging;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UUID uniqueId = UUID.randomUUID();
        MDC.put("requestId", uniqueId.toString());
        MDC.put("ip", servletRequest.getRemoteAddr());

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
                httpServletResponse
        );
        filterChain.doFilter(servletRequest, responseWrapper);
    }
}
