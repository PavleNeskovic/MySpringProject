package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MessageDto;
import com.example.model.Message;
import com.example.model.User;
import com.example.repository.MessageRepository;
import com.example.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	

	public Collection<Message> getAll(){
		Collection<Message> messages = messageRepository.findAll();
		return messages;
	}
	
	public Message create(MessageDto newMessage){
//		if (newMessage.getSenderEmail() != null) {
//			return null;
//		}
		Message message = new Message();
		message.setText(newMessage.getText());
		message.setRecever(userRepository.findOneByEmail(newMessage.getReceverEmail()).get());
		message.setSender(userRepository.findOneByEmail(newMessage.getSenderEmail()).get());
		return messageRepository.save(message);

	}
	
	public Collection<Message> getConversation(User sender, User recever){
//		String senderEmail = sender.getEmail();
//		String receverEmail = recever.getEmail();
		Collection<Message> messages = messageRepository.findByCustomQuery(sender, recever);
		return messages;
	}
}
