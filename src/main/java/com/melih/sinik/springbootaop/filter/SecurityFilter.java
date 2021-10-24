package com.melih.sinik.springbootaop.filter;

import com.melih.sinik.springbootaop.domain.AuthenticationContext;
import com.melih.sinik.springbootaop.service.auth.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Melih ŞİNİK
 * @since 17.10.2021
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = httpServletRequest.getHeader("Authorization");
        boolean isValid = false;
        String token = null;

        if (authHeader != null && authHeader.contains("Bearer")) {
            token = authHeader.substring(7);

            isValid = tokenManager.tokenValidate(token);
        }

        if (isValid && SecurityContextHolder.getContext().getAuthentication() == null) {
            var authenticationContext = new AuthenticationContext(token, true);
            SecurityContextHolder.getContext().setAuthentication(authenticationContext);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
