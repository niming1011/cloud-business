package com.powernode.config;


import com.powernode.constant.AuthConstants;

import com.powernode.handler.MyAuthenticationFailureHandler;
import com.powernode.handler.MyAuthenticationSuccessHandler;
import com.powernode.handler.MyLogoutSuccessHandler;
import com.powernode.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;

@Configuration
public class AuthSecurityConfig {
    @Autowired
    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    public MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    public MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource1(){
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
        return httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource1()))
                .csrf(csrf->csrf.disable())
                .sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(formLogin->{
                    formLogin.loginProcessingUrl(AuthConstants.LOGINURL)
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler);
                })
                .logout(logout->{
                    logout.logoutUrl(AuthConstants.LOGOUTURL)
                            .logoutSuccessHandler(myLogoutSuccessHandler);
                })
                .build();
    }

}
