package com.security.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.security.entity.UserDetail;

@RepositoryRestResource
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
	
	//Use Entity Name
	@Query("From UserDetail where firstName = ?1")
	List<UserDetail> findByFirstName(String firstName);
	
	@Query("From UserDetail where userName = ?1")
	List<UserDetail> findByUserName(String userName);	
	
	@Query("Select a From UserDetail a where a.firstName = ?1 and a.lastName = ?2")
	List<UserDetail> findByFirstNameAndLastName(String firstName, String lastName);
	
	@Query("From UserDetail where firstName = ?1 Order by lastName ASC ")
	List<UserDetail> findByFirstNameOrdeByLastName(String firstName);
	
	//@Query("From user_detail where first_name = ?1")
	//List<UserDetail> findByFirstName(String firstName, Sort sort);
	
	@Query("From UserDetail where UCASE(firstName) like CONCAT('%', UPPER(?1), '%')")
	List<UserDetail> findByFirstNameContainingIgnoreCase(String firstName);
	
	//NativeQuery use TableName
	@Query(value = "Select * From user_detail where first_name = :firstName", nativeQuery = true)
	List<UserDetail> findUserDetail(@Param("firstName") String firstName);	
	
	
}

	