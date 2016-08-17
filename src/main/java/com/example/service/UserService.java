package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		//novo
		User user = new User(newUser);
		//user.setEmail(newUser.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
		System.out.println("##################### Hashed password: ###################" + user.getPassword());
		//user.setRole(newUser.get);
		//User user = userRepository.save(new User(newUser));
		userRepository.save(user);
		return user.transferToDto();
	}
	
	public UserDto login(String username, String password){
		User user = userRepository.findByUsernameAndPassword(username, password);
		UserDto response = user.transferToDto();
		return response;
	}
	
	public UserDto getUserByEmail(String email) {
		User user = userRepository.findOneByEmail(email);
		UserDto response = user.transferToDto();
		return response;
	}
}
