package com.example.dto;

import com.example.model.Role;

public class UserDto {

	private String email;
	private String username;
	private String password;
	private Role role;

	public UserDto() {
	}

	// public UserDto(String email) {
	// this.email = email;
	// }

	public UserDto(String email, String username, String password, Role role) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
