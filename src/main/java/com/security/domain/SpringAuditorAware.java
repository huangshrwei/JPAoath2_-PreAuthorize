package com.security.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.security.service.UmsAdminService;

import lombok.extern.slf4j.Slf4j;


@Component
public class SpringAuditorAware implements AuditorAware<Integer>{ 
    
    private AdminUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return (AdminUserDetails) principal;
    }

    public Integer getId() {
    	Long userid = getUserDetails().getUserid();
    	int intUserid = userid.intValue();    	
        return intUserid;
    }

    public String getName() {
        return getUserDetails().getUsername();
    }

    public String getRole() {
        return getUserDetails().getRole();
    }
    
	@Override
	public Optional<Integer> getCurrentAuditor(){
		if (getUserDetails() != null) {			
			return Optional.of(getId());
		}
		return Optional.of(null);
	}
	
}
