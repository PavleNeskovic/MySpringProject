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
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MessageDto;
import com.example.dto.UserCreateForm;
import com.example.model.Message;
import com.example.model.User;
import com.example.service.MessageService;

@RestController
public class MessageController {
	
	private MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
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
    public ResponseEntity<MessageDto> getMessageCreatePage(){
    	return new ResponseEntity<MessageDto>(new MessageDto(), HttpStatus.OK);
}

	@RequestMapping(
			value = "/message",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> createMessage(@RequestBody MessageDto newMessage){		
		return new ResponseEntity<>(messageService.create(newMessage), HttpStatus.CREATED);
	}
	
	 @RequestMapping(
	    		value = "/message/{sender}/{recever}",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Collection<Message>> getConversation(
	    		@PathVariable("sender") User sender,
	    		@PathVariable("recever") User recever
	    		){
	    	Collection<Message> messages = messageService.getConversation(sender, recever);
	    	return new ResponseEntity<Collection<Message>>(messages, HttpStatus.OK);
	}
	

	
}
