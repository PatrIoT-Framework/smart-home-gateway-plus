package io.patriotframework.smarthomeplusgateway;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for checking if the IP address is in the whitelist
 */
@Component
public class IpWhiteListing  extends OncePerRequestFilter {

    @Value("${whitelist.allowedSubnets}")
    private List<String> allowedSubnets;

    /**
     * This method is responsible for checking if the IP address is in the whitelist
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("IPWhiteListing");
        System.out.println(allowedSubnets);

        for (String subnet: allowedSubnets) {
            System.out.println(subnet);
            IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
            if (ipAddressMatcher.matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        throw new BadCredentialsException("Invalid IP address");
    }

}
