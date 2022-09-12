package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.common.JwtAuthenticationTokenFilter;

@Configuration
@EnableGlobalMethodSecurity(
  prePostEnabled = true, 
  securedEnabled = true, 
  jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
	
	//@Autowired
	//UserProfileService userProfileService;
	
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;	 
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        
        
        //允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();               
        
    	/*
        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").permitAll()
                .anyRequest().authenticated();
        */
    	
        httpSecurity.csrf()// 由於使用的是JWT，我们这里不需要csrf
        .disable()
        .sessionManagement()// 基於token，所以不需要session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                //"/userDetail/**",
                "/swagger-resources/**",
                "/v2/api-docs/**"
        )
        .permitAll()
        .antMatchers("/admin/login", "/admin/register")// 登錄注册要允许匿名访问
        .permitAll()
        .antMatchers(HttpMethod.OPTIONS)//跨域请求會先進行一次options请求
        .permitAll()
//        .antMatchers("/**")//测试时全部允許访问
//        .permitAll()
        .anyRequest()// 除上面外的所有请求全部需要認證
        .authenticated();
        
        // 禁用缓存
        httpSecurity.headers().cacheControl();       

        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
        //有動態權限配置时添加動態權限校验過濾器
            
        
        return httpSecurity.build();	
    }
}
