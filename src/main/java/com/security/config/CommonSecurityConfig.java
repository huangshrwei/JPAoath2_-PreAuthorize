package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.common.JwtAuthenticationTokenFilter;
import com.security.common.TerminateBean;
import com.security.common.utils.JwtTokenUtil;

/**
 * SpringSecurity通用配置
 * 包括通用Bean、Security通用Bean及動態權限通用Bean
 */
@Configuration
public class CommonSecurityConfig {


    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }    
     
    
}