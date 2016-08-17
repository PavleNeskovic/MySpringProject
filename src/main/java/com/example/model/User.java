package com.example.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import com.example.dto.UserDto;

@Entity
public class User {

	//consider adding id field as advised in a tutorial
	@Id
	private String email;
	
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {
		super();
	}

//	public User(String email) {
//		this.email = email;
//	}
	
	
	
	public User(UserDto dto) {
		this.email = dto.getEmail();
		//dodatak
		this.username = dto.getUsername();
		this.password = dto.getPassword();
	}

	public User(String email, String username, String password, Role role) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDto transferToDto() {
		UserDto dto = new UserDto();
		dto.setEmail(getEmail());
		//novo - dodatak za username i password
		dto.setUsername(getUsername());
		dto.setPassword(getPassword());
		return dto;
	}

	public void updateFromDto(UserDto dto) {
		if (StringUtils.isEmpty(dto.getEmail())) {
			this.setEmail(dto.getEmail());
		}
	}
	
}
