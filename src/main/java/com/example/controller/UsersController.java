package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserCreateForm;
import com.example.dto.UserDisplayData;
import com.example.model.User;
import com.example.service.UserService;

@RestController
public class UsersController {
	
	private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
	
	@RequestMapping(
			value = "/contacts",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<UserDisplayData>> getAllUsers(){
		
		Collection<UserDisplayData> users = userService.getAllUsers();
		
		return new ResponseEntity<Collection<UserDisplayData>>(users, HttpStatus.OK);
	}
	
	//adding pagination
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public Page<UserDisplayData> list(Pageable pageable){
		Page<UserDisplayData> users = userService.listAllByPage(pageable);
		return users;
	}
}
