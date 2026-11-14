package com.powernode.config;


import cn.hutool.json.JSONUtil;
import com.powernode.constant.AuthConstants;
import com.powernode.constant.BusinessEnum;
import com.powernode.constant.ResourceConstants;

import com.powernode.filter.TokenTranslationFilter;
import com.powernode.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ResourceSecurityConfig {

    @Autowired
    private TokenTranslationFilter tokenTranslationFilter;
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**",config);
        return source;

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf->csrf.disable())
                .sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests->{
                    authorizeHttpRequests
                            .requestMatchers(ResourceConstants.RESOURCE_ALLOW_URLS)
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .exceptionHandling(exceptionHandling->{
                    exceptionHandling
                            .authenticationEntryPoint(authenticationEntryPoint())
                            .accessDeniedHandler(accessDeniedHandler());
                })
                .addFilterBefore(tokenTranslationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint ( ){
        return(request, response, authException) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            Result fail = Result.fail(BusinessEnum.UN_AUTHORIZATION);
            String jsonStr = JSONUtil.toJsonStr(fail);
            PrintWriter writer = response.getWriter();
            writer.write(jsonStr);
            writer.flush();
            writer.close();
        };

    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return ((request, response, accessDeniedException) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            Result fail = Result.fail(BusinessEnum.ACCESS_DENY_FAIL);
            String jsonStr = JSONUtil.toJsonStr(fail);
            PrintWriter writer = response.getWriter();
            writer.write(jsonStr);
            writer.flush();
            writer.close();
        });
    }
}

