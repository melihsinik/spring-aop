package com.melih.sinik.springbootaop.config;


import com.melih.sinik.springbootaop.filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Melih ŞİNİK
 * @since 15.10.2021
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityFilter securityFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/auth/{userName}/token").permitAll()
                .antMatchers("/management/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
