package com.example.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.model.Role;
import com.example.model.Status;

public class UserDisplayData {
	@NotEmpty 
	private String email = "";
	
	@NotEmpty 
	private String username = "";
    
    @NotNull
    private Role role = Role.ROLE_USER;
    
    @NotNull
    private Status status = Status.NOT_AVAILABLE;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
