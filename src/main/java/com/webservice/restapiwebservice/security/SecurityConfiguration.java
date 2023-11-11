package com.webservice.restapiwebservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

//@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception{
        MvcRequestMatcher.Builder mvcRequestMatcher = new MvcRequestMatcher.Builder(introspector);

        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers(toH2Console()).disable())

                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(toH2Console()).permitAll()
                                .requestMatchers(mvcRequestMatcher.pattern(HttpMethod.OPTIONS, "/**")).permitAll()
                                .anyRequest().authenticated()
                );

        http.headers(headers -> headers.frameOptions((frameOptions) -> frameOptions.disable()));
        http.sessionManagement(session  -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(withDefaults());
        return http.build();
    }

}
