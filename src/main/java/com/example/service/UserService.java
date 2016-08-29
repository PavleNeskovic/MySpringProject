package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.UserCreateForm;
import com.example.dto.UserDisplayData;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface{

	 private final UserRepository userRepository;

	    @Autowired
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public User getUserById(long id) {
	        return userRepository.findOne(id);
	    }

	    @Override
	    public Optional<UserDisplayData> getUserByEmail(String email) {
	    	Optional<UserDisplayData> userDto = Optional.of(new UserDisplayData());
	    	User user = userRepository.findOneByEmail(email).get();
    		userDto.get().setEmail(user.getEmail());
    		userDto.get().setUsername(user.getUsername());
    		userDto.get().setRole(user.getRole());
    		userDto.get().setStatus(user.getStatus());
    		return userDto;
	    }
	    
	    @Override
	    public Optional<UserDisplayData> getUserByUsername(String name) {
	    	Optional<UserDisplayData> userDto = Optional.of(new UserDisplayData());
	    	User user = userRepository.findOneByUsername(name).get();
    		userDto.get().setEmail(user.getEmail());
    		userDto.get().setUsername(user.getUsername());
    		userDto.get().setRole(user.getRole());
    		userDto.get().setStatus(user.getStatus());
    		return userDto;
	    }
	    
	    //cheat
	    public Optional<User> getUserByEmail2(String email) {
	    	
	    	return userRepository.findOneByEmail(email);
    		
	    }

	    @Override
	    public Collection<UserDisplayData> getAllUsers() {
	    	Collection<UserDisplayData> userDtos = new ArrayList<UserDisplayData>();
	    	Collection<User> users = userRepository.findAll(new Sort("email"));
	    	for (User user : users) {
	    		UserDisplayData userDto = new UserDisplayData();
	    		userDto.setEmail(user.getEmail());
	    		userDto.setUsername(user.getUsername());
	    		userDto.setRole(user.getRole());
	    		userDto.setStatus(user.getStatus());
	    		userDtos.add(userDto);
			}
	    	return userDtos;
	    }

	    @Override
	    public User create(UserCreateForm form) {
	        User user = new User();
	        user.setEmail(form.getEmail());
	        user.setUsername(form.getUsername());
	        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
	        user.setRole(Role.ROLE_USER);
	        user.setStatus(form.getStatus());
	        return userRepository.save(user);
	    }
	    

	    public User createAdmin(UserCreateForm form) {
	        User user = new User();
	        user.setEmail(form.getEmail());
	        user.setUsername(form.getUsername());
	        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
	        user.setRole(Role.ROLE_ADMIN);
	        user.setStatus(form.getStatus());
	        return userRepository.save(user);
	    }

}
