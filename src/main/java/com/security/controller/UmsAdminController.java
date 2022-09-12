package com.security.controller;

import com.security.common.api.CommonResult;
import com.security.dto.UserProfileDto;
import com.security.entity.UserProfile;
import com.security.service.UmsAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 */
@RestController
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "登錄後返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username, @RequestParam String password) {
        String token = adminService.login(username, password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    } 
    
    
    @ApiOperation(value = "註冊, ROLE: {ADMIN/USER}")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username, @RequestParam String password, @RequestParam String Role) {
    	UserProfileDto userProfileDto = new UserProfileDto();
    	userProfileDto.setUserName(username);
    	userProfileDto.setRole(Role);
    	userProfileDto.setUserPassword(password);
    	UserProfile userProfile = adminService.register(userProfileDto);
    	if (userProfile.getUserId()==null) {
    		return CommonResult.validateFailed("用户名重複或密码規則錯誤");
    	}    	
    	return CommonResult.success(userProfile);
    }      
    
}
