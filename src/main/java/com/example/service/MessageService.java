package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	

	public Collection<Message> getAll(){
		Collection<Message> messages = messageRepository.findAll();
		return messages;
	}
	
	public Message create(Message newMessage){
		if (newMessage.getId() != null) {
			return null;
		}
		Message message = messageRepository.save(newMessage);
		return message;
	}
}
