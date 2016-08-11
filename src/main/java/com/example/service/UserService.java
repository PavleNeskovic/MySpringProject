package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Collection<UserDto> getAll(){
		Collection<User> users = userRepository.findAll();
		
		Collection<UserDto> response = new ArrayList<UserDto>();
		
		for (User user : users) {
			response.add(user.transferToDto());
		}
		
		return response;
	}
	
	public UserDto create(UserDto newUser){
		if (newUser.getEmail() == null) {
			return null;
		}
		User user = userRepository.save(new User(newUser));
		return user.transferToDto();
	}
}
