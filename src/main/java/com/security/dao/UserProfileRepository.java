package com.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.security.entity.UserProfile;

@RepositoryRestResource
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	//Use Entity Name
	@Query("From UserProfile where userName = ?1")
	UserProfile getUsername(String userName);	
	
}
