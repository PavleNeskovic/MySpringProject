package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MessageDto;
import com.example.dto.UserDisplayData;
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
	
	@Autowired
	private UserService userService;
	

	public Collection<Message> getAll(){
		Collection<Message> messages = messageRepository.findAll();
		return messages;
	}
	
	//TODO: email radi sa username-om jer treba da se setuje da username bude unique u bazi. Hack.
	public Message create(MessageDto newMessage){
//		if (newMessage.getSenderEmail() != null) {
//			return null;
//		}
		Message message = new Message();
		message.setText(newMessage.getText());
		message.setRecever(userService.getUserByUsername(newMessage.getReceverEmail()));
//		//message.setRecever(userRepository.findOneByEmail(newMessage.getReceverEmail()).get());
//		message.setSender(userRepository.findOneByEmail(newMessage.getSenderEmail()).get());
		message.setSender(userService.getUserByUsername(newMessage.getSenderEmail()));
		return messageRepository.save(message);

	}
	
	public Collection<Message> getConversation(User sender, String receverUsername){
		User recever = userRepository.findOneByUsername(receverUsername).get();
		Collection<Message> messages = messageRepository.findByCustomQuery(sender, recever);
		return messages;
	}
}
