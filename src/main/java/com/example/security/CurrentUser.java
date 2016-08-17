package com.example.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.dto.UserDto;
import com.example.model.Role;

public class CurrentUser extends org.springframework.security.core.userdetails.User{
	private com.example.dto.UserDto user;

	public CurrentUser(UserDto user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public com.example.dto.UserDto getUser() {
		return user;
	}

	public Role getRole(){
		return user.getRole();
	}
	
	public String getEmail(){
		return user.getEmail();
	}
	
}
