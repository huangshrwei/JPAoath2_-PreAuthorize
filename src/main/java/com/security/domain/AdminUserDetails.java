package com.security.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by macro on 2020/10/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class AdminUserDetails implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7514871011995452733L;
	private String username;
	private long userid;
    private String password;
    private String role;
    
    private List<String> authorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return this.authorityList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        return this.authorityList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //return authorities;
    }
    
    public long getUserid() {
        return this.userid;
    }    

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    
    public String getRole() {
        return this.role;
    }    

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
