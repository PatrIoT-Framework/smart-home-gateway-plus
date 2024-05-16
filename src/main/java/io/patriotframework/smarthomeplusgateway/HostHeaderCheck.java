package io.patriotframework.smarthomeplusgateway;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * This class is responsible for checking if the host header is allowed
 */
@Component
public class HostHeaderCheck extends OncePerRequestFilter {

    /**
     * This method is responsible for checking if the host header is allowed
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws java.io.IOException
     */
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {
            System.out.println("HostHeaderCheck");
            String host = request.getHeader("Host");
            System.out.println(host);
            if (host == null) {
                throw new ServletException("Host header is missing");
            }
            if (!host.equals("localhost")) {
                throw new ServletException("Host header is invalid");
            }
            filterChain.doFilter(request, response);
        }
}
