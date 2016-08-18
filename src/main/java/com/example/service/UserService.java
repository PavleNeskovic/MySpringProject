package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.dto.UserCreateForm;
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
	    public Optional<User> getUserByEmail(String email) {
	        return userRepository.findOneByEmail(email);
	    }

	    @Override
	    public Collection<User> getAllUsers() {
	        return userRepository.findAll(new Sort("email"));
	    }

	    @Override
	    public User create(UserCreateForm form) {
	        User user = new User();
	        user.setEmail(form.getEmail());
	        user.setUsername(form.getUsername());
	        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
	        user.setRole(form.getRole());
	        user.setStatus(form.getStatus());
	        return userRepository.save(user);
	    }
}
