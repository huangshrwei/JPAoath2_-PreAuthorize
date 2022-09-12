package com.security.service.impl;

import com.security.common.utils.JwtTokenUtil;
//import com.security.config.UserProfileService;
import com.security.dao.UserProfileRepository;
import com.security.domain.AdminUserDetails;
import com.security.dto.UserProfileDto;
import com.security.entity.UserProfile;
import com.security.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.*;

/**
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    /**
     * 存放默认用户信息
     */
    private List<AdminUserDetails> adminUserDetailsList = new ArrayList<>();
    private List<UserProfile> userProfileList = new ArrayList<>();
    /**
     * 存放默认资源信息
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired 
    UserProfileRepository userProfileRepository;

    //@Autowired 
    //UserProfileService userProfileService;
    
    @PostConstruct
    private void init(){
    	
    	userProfileList = userProfileRepository.findAll();    	
        for (int i = 0; i<userProfileList.size(); i++) {
        	
        	List<String> roleList = new ArrayList<>();         	        
        	
			switch(userProfileList.get(i).getRole()){
			    case "ADMIN" :
			    	roleList.add("ROLE_ADMIN");
			    	roleList.add("ROLE_USER");			    	
			       break;
			    case "USER" :
			    	roleList.add("ROLE_USER");			    	
			       break;
			    default :
			    	roleList.clear();
			}        	
        	
			log.info("init UmsAdminService.");
        	//log.info("UserName: " +userProfileList.get(i).getUserName());
        	//log.info("UserPassword: " +userProfileList.get(i).getUserPassword());        	
        	//log.info("Role: " +userProfileList.get(i).getRole());			
        	//log.info("RoleList: " +roleList.toString());			
			
            adminUserDetailsList.add(AdminUserDetails.builder()
            		.userid(userProfileList.get(i).getUserId())
                    .username(userProfileList.get(i).getUserName())
                    .password(passwordEncoder.encode(userProfileList.get(i).getUserPassword()))
                    .role(userProfileList.get(i).getRole())
                    .authorityList(roleList)                    
                    .build());
        }   
    }

    @Override
    public AdminUserDetails getAdminByUsername(String username) {
        List<AdminUserDetails> findList = adminUserDetailsList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        log.info("getAdminByUsername: " + findList.toString());
        if(findList.size()>0){
            return findList.get(0);
        }
        return null;
    }    
    

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
        	AdminUserDetails userDetails = getAdminByUsername(username);
            if(userDetails==null){
                return token;
            }
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密碼錯誤");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登錄異常:{}", e.getMessage());
        }
        return token;
    }
    
    @Override
    public UserProfile register(UserProfileDto userProfileDto) {
    	
    	UserProfile userProfile = new UserProfile(); 
    	boolean isExist = false;   	
    	
    	AdminUserDetails user = getAdminByUsername(userProfileDto.getUserName());
    	if (user!=null) {
    		isExist = true;
    	}    	
    	
    	if (isExist == false) {
    		try {
    			
    			boolean isMatch = false;
    			if (userProfileDto.getRole().equals("ADMIN")) {
    				//密碼必須為長度6~16位碼大小寫英文加數字
        			String regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w]{6,16}$";
        			isMatch = Pattern.matches(regexp, userProfileDto.getUserPassword());
    			}else {
    				//密碼必須為長度6~16
    				String regexp = "^[\\w]{6,16}$";
    				isMatch = Pattern.matches(regexp, userProfileDto.getUserPassword());
    			}
    			
    			if (isMatch) {
    				
    				UserProfile profile = new UserProfile();    				
    				profile.setRole(userProfileDto.getRole());
    				profile.setUserName(userProfileDto.getUserName());
    				profile.setUserPassword(userProfileDto.getUserPassword());
    				userProfile = userProfileRepository.save(profile);
    			}else {
    				throw new Exception("密碼格式不符");
    			}
    			
    			init();
    			
    		}catch(Exception e){
    			log.warn("註冊異常:{}", e.getMessage());
    		}
    	}
    	
    	return userProfile;
    }      
    
}
