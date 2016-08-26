package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDto;
import com.example.model.Message;
import com.example.model.User;
import com.example.service.MessageService;
import com.example.service.UserService;

@RestController
public class Controller {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(
			value = "/api",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<UserDto>> getAllUsers(){
		
		Collection<UserDto> users = userService.getAll();
		
		return new ResponseEntity<Collection<UserDto>>(users, HttpStatus.OK);
	}
	
	
	//THIS WILL BE SIGNUP
	//dodati valid

	@RequestMapping(
			value = "/api",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> createUser(@RequestBody UserDto newUser){
		
		UserDto user = userService.create(newUser);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/api/message",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Message>> getAllMessages(){
		
		Collection<Message> messages = messageService.getAll();
		
		return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
	}
	

	@RequestMapping(
			value = "/api/message",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> createMessage(@RequestBody Message newMessage){
		
		Message message = messageService.create(newMessage);
		
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	 @RequestMapping(
	    		value = "/api/{sender}/{recever}",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Collection<Message>> getConversation(
	    		@PathVariable("sender") User sender,
	    		@PathVariable("recever") User recever
	    		){
	    	Collection<Message> messages = messageService.getConversation(sender, recever);
	    	return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> login(@RequestBody UserDto user) {
		user = userService.login(user.getUsername(), user.getPassword());
		System.out.println("#########LOGIN LOGIN LOGIN###########");
		if(user == null) {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
