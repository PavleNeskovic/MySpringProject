package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.UserService;
@Service
public class CurrentUserDetailsService implements UserDetailsService {

	private final UserService userService;
	
	@Autowired
	public CurrentUserDetailsService(UserService userService){
		this.userService = userService;
	}
	
	@Override
	public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDto user = userService.getUserByEmail(email);
        return new CurrentUser(user);
	}

}
