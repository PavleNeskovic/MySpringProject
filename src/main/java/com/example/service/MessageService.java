package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Message;
import com.example.model.User;
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
	
	public Collection<Message> getConversation(User sender, User recever){
//		String senderEmail = sender.getEmail();
//		String receverEmail = recever.getEmail();
		Collection<Message> messages = messageRepository.findByCustomQuery(sender, recever);
		return messages;
	}
}
