package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MessageCreateDto;
import com.example.dto.MessageDto;
import com.example.dto.UserDisplayData;
import com.example.model.Message;
import com.example.model.User;
import com.example.service.MessageService;
import com.example.service.UserService;

@RestController
public class MessageController {
	
	private MessageService messageService;
	private UserService userService;
	
	@Autowired
	public MessageController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@RequestMapping(
			value = "/message/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Message>> getAllMessages(){
		
		Collection<Message> messages = messageService.getAll();
		
		return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
	}
	
	
    @RequestMapping(
    		value = "/message",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageCreateDto> getMessageCreatePage(){
    	return new ResponseEntity<MessageCreateDto>(new MessageCreateDto(), HttpStatus.OK);
}

	@RequestMapping(
			value = "/message",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> createMessage(@RequestBody MessageCreateDto newMessage){	
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User sender = userService.getUserByUsername(auth.getName());
			System.out.println(sender.getUsername());
			System.out.println(newMessage.getUsernameSender());
			System.out.println(!sender.getUsername().equals(newMessage.getUsernameSender()));
			if (!sender.getUsername().equals(newMessage.getUsernameSender())) {
				System.out.println("nisam ovde");
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			//SecurityContextHolder.clearContext();
			messageService.create(newMessage);
		return new ResponseEntity<>(messageService.create(newMessage), HttpStatus.CREATED);
	}
	
//	 @RequestMapping(
//	    		value = "/message/{recever}", 
//	            method = RequestMethod.GET,
//	            produces = MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<Collection<Message>> getConversation(
//	    		@PathVariable("recever") String receverUsername){
//		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			User sender = userService.getUserByUsername(auth.getName());
//			//SecurityContextHolder.clearContext();
//	    	Collection<Message> messages = messageService.getConversation(sender, receverUsername);
//	    	return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
//	}
	
	@RequestMapping(
    		value = "/message/{recever}", 
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<MessageCreateDto>> getConversation(
    		@PathVariable("recever") String receverUsername){
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User sender = userService.getUserByUsername(auth.getName());
		//SecurityContextHolder.clearContext();
    	Collection<MessageCreateDto> messages = messageService.getConversation(sender, receverUsername);
    	return new ResponseEntity<Collection<MessageCreateDto>>(messages, HttpStatus.OK);
}
	

	
}
