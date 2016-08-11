package com.example.dto;

import com.example.model.User;

public class MessageDto {
	
	private String text;
	
	private String senderEmail;
	
	private String receverEmail;
	
	

	public MessageDto() {
		super();
	}

	public MessageDto(String text, String senderEmail, String receverEmail) {
		super();
		this.text = text;
		this.senderEmail = senderEmail;
		this.receverEmail = receverEmail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceverEmail() {
		return receverEmail;
	}

	public void setReceverEmail(String receverEmail) {
		this.receverEmail = receverEmail;
	}
	
	
	
	

}
