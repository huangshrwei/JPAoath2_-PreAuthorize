package com.security.entity;

import java.util.Date;
//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_profile")
@Entity
@Data
public class UserProfile {
	
	/*
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id") 
    @EqualsAndHashCode.Exclude
    private Set<UserDetail> userDetail;
    */

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)		
    @Column(name = "user_id")	
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;
    
    private String role;    

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private Date creationDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate; 
    

}