package com.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.dao.UserDetailRepository;

import com.security.dto.UserDetailDto;
import com.security.entity.UserDetail;
import com.security.service.UserDetailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailService{

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetail> listAllUserDetail() {
        return userDetailRepository.findAll();
    }

    @Override
    @Transactional  
    public int createUserDetail(UserDetailDto userDetailDto) {
    	UserDetail saveUserDetail = new UserDetail();
    	saveUserDetail.setAddress(userDetailDto.getAddress());
    	saveUserDetail.setBirthday(userDetailDto.getBirthday());
    	saveUserDetail.setEdu(userDetailDto.getEdu());
    	saveUserDetail.setEmail(userDetailDto.getEmail());
    	saveUserDetail.setFirstName(userDetailDto.getFirstName());
    	saveUserDetail.setLastName(userDetailDto.getLastName());
    	saveUserDetail.setPhoto(userDetailDto.getPhoto());
    	saveUserDetail.setProfessional(userDetailDto.getProfessional());
    	saveUserDetail.setSex(userDetailDto.getSex());
    	saveUserDetail.setUserName(userDetailDto.getUserName());    		
    	
    	int i = 0;
    	try {
    		userDetailRepository.save(saveUserDetail);    	
    	}catch (Exception e) {
    		log.debug("createUserDetail: " + e.getMessage());
    		i = -1;
    	}     
    	
        return i;
    }

    @Override
    @Transactional    
    public int updateUserDetail(Long id, UserDetailDto userDetailDto) {
    	UserDetail saveUserDetail = new UserDetail();
    	saveUserDetail.setAddress(userDetailDto.getAddress());
    	saveUserDetail.setBirthday(userDetailDto.getBirthday());
    	saveUserDetail.setEdu(userDetailDto.getEdu());
    	saveUserDetail.setEmail(userDetailDto.getEmail());
    	saveUserDetail.setFirstName(userDetailDto.getFirstName());
    	saveUserDetail.setLastName(userDetailDto.getLastName());
    	saveUserDetail.setPhoto(userDetailDto.getPhoto());
    	saveUserDetail.setProfessional(userDetailDto.getProfessional());
    	saveUserDetail.setSex(userDetailDto.getSex());
    	saveUserDetail.setUserName(userDetailDto.getUserName());    	
    	int i = 0;
    	try {
    		userDetailRepository.save(saveUserDetail);    	
    	}catch (Exception e) {
    		log.debug("createUserDetail: " + e.getMessage());
    		i = -1;
    	}	
        return i;
    }

    @Override
    @Transactional    
    public int deleteUserDetail(Long id) {
    	int i = 0;
    	try {
    		userDetailRepository.deleteById(id);
    		i = 1;
    	}catch (Exception e) {
    		i = -1;
    	}
        return i;
    }

    @Override
    public List<UserDetail> listUserDetail(int pageNum, int pageSize) {
    	Pageable pageable = PageRequest.of(pageNum-1, pageSize);    	 
    	Page<UserDetail> page = userDetailRepository.findAll(pageable);
    	return page.getContent();
    }

    @Override
    public UserDetail getUserDetail(Long id) {
    	Optional<UserDetail> optUserDetail = userDetailRepository.findById(id);
    	if (!optUserDetail.isEmpty()) {
    		UserDetail userDetail = optUserDetail.get();
    		return userDetail;
    	}else {
    		return null;
    	}
         
    }	
	
}
