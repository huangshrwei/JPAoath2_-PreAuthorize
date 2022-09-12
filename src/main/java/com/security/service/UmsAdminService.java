package com.security.service;

import com.security.domain.AdminUserDetails;
import com.security.dto.UserProfileDto;
import com.security.entity.UserProfile;

import java.util.List;

public interface UmsAdminService {

    /**
     * 依照使用者名稱取得使用者資訊
     */
    AdminUserDetails getAdminByUsername(String username);	
	
    /**
     * 登錄使用者帳號密碼
     */
    String login(String username, String password);
        
    /**
     * 註冊新使用者
     */
    UserProfile register(UserProfileDto userProfileDto);
    
}
