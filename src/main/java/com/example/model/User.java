package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import com.example.dto.UserDto;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	public User() {
		super();
	}

	public User(String email) {
		this.email = email;
	}

	public User(UserDto dto) {
		this.email = dto.getEmail();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto transferToDto() {
		UserDto dto = new UserDto();
		dto.setEmail(getEmail());
		return dto;
	}

	public void updateFromDto(UserDto dto) {
		if (StringUtils.isEmpty(dto.getEmail())) {
			this.setEmail(dto.getEmail());
		}
	}
	
}
