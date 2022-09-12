package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.security.domain.SpringAuditorAware;

@EnableJpaAuditing(auditorAwareRef="auditorProvider")
@Configuration
public class PersistenceConfig {
	
    @Bean
    AuditorAware<Integer> auditorProvider() {
        return new SpringAuditorAware();
    }
    
}
