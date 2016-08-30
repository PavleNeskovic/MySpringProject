package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.example.dto.MessageCreateDto;
import com.example.dto.MessageDto;
import com.example.dto.UserCreateForm;


@Entity
public class Message {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	
	@ManyToOne
	//@PrimaryKeyJoinColumn
	private User sender;
	
	@ManyToOne
	//@PrimaryKeyJoinColumn
	private User recever;

	public Message() {
		super();
	}
	
	public Message(MessageDto dto){
		this.text = dto.getText();
		this.sender.setEmail(dto.getSenderEmail());
		this.recever.setEmail(dto.getReceverEmail());
	}

	public Message(Long id, String text, User sender, User recever) {
		super();
		this.id = id;
		this.text = text;
		this.sender = sender;
		this.recever = recever;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecever() {
		return recever;
	}

	public void setRecever(User recever) {
		this.recever = recever;
	}
	
	public MessageDto transferToDto() {
		MessageDto dto = new MessageDto();
		dto.setText(getText());
		dto.setSenderEmail(getSender().getEmail());
		dto.setReceverEmail(getRecever().getEmail());
		return dto;
	}
	
	public MessageCreateDto transferToCreateDto() {
		MessageCreateDto dto = new MessageCreateDto();
		dto.setText(getText());
		dto.setUsernameSender(getSender().getUsername());
		dto.setUsernameReceiver(getRecever().getUsername());
		return dto;
	}
}