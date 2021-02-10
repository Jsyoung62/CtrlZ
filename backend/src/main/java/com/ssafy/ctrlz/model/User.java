package com.ssafy.ctrlz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	
	private String userGid;
	private char userType;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userIntroduce;
	private String userImage;

}