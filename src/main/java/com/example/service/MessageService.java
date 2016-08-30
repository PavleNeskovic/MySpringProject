package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MessageCreateDto;
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
//	public Message create(MessageDto newMessage){
////		if (newMessage.getSenderEmail() != null) {
////			return null;
////		}
//		Message message = new Message();
//		message.setText(newMessage.getText());
//		message.setRecever(userService.getUserByUsername(newMessage.getReceverEmail()));
////		//message.setRecever(userRepository.findOneByEmail(newMessage.getReceverEmail()).get());
////		message.setSender(userRepository.findOneByEmail(newMessage.getSenderEmail()).get());
//		message.setSender(userService.getUserByUsername(newMessage.getSenderEmail()));
//		return messageRepository.save(message);
//
//	}
	
	public MessageCreateDto create(MessageCreateDto newMessage) {
		Message message = new Message();
		message.setText(newMessage.getText());
		//message.setRecever(userService.getUserDisplayDataByUsername(newMessage.getUsernameReceiver()).get());
		//message.setRecever(userService.getUserByUsername(newMessage.getUsernameReceiver()));
		message.setRecever(userService.getUserByUsername(newMessage.getUsernameReceiver()));
		message.setSender(userService.getUserByUsername(newMessage.getUsernameSender()));
		System.out.println("receiver:" + message.getRecever());
		System.out.println("sender:" + message.getSender());
		messageRepository.save(message);
		return newMessage;
	}
	
//	public Collection<MessageDto> getConversation(User sender, String receverUsername){
//		User recever = userRepository.findOneByUsername(receverUsername).get();
//		Collection<Message> messages = messageRepository.findByCustomQuery(sender, recever);
//		Collection<MessageDto> messagesDtos = new ArrayList<>();
//		for (Message message : messages) {
//			messagesDtos.add(message.transferToDto());
//		}
//		return messagesDtos;
//	}
	public Collection<MessageCreateDto> getConversation(User sender, String receverUsername){
		User recever = userRepository.findOneByUsername(receverUsername).get();
		Collection<Message> messages = messageRepository.findByCustomQuery(sender, recever);
		Collection<MessageCreateDto> messagesDtos = new ArrayList<>();
		for (Message message : messages) {
			messagesDtos.add(message.transferToCreateDto());
		}
		return messagesDtos;
	}
}