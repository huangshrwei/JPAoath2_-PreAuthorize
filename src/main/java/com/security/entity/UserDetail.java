package com.security.entity;

import java.util.Date;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Table(name = "user_detail")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserDetail {

	/*
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",insertable = false, updatable = false)    
    private UserProfile userProfile;	
	*/
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)		
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    private String sex;

    @Column(name = "edu")
    private String edu;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "professional")
    private String professional;

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private Date creationDate;   
    
    @CreatedBy
    @Column(name = "created_by")
    private Integer createdBy;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;     
    
    @LastModifiedBy
    @Column(name = "updated_by")
    private Integer updatedBy;
    
    @Column(name = "photo")
    private byte[] photo;
}