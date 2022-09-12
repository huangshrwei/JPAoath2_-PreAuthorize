package com.security.service;

import java.util.List;

import com.security.dto.UserDetailDto;
import com.security.entity.UserDetail;

public interface UserDetailService {

    List<UserDetail> listAllUserDetail();

    int createUserDetail(UserDetailDto userDetailDto);

    int updateUserDetail(Long id, UserDetailDto userDetailDto);

    int deleteUserDetail(Long id);

    List<UserDetail> listUserDetail(int pageNum, int pageSize);

    UserDetail getUserDetail(Long id);	
	
}
