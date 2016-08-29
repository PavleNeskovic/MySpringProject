package com.example.dto;

public class MessageCreateDto {
	private String text;
	private String usernameReceiver;
	private String usernameSender;

	public MessageCreateDto() {
		super();
	}

	public MessageCreateDto(String text, String usernameReceiver, String usernameSender) {
		super();
		this.text = text;
		this.usernameReceiver = usernameReceiver;
		this.usernameSender = usernameSender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsernameReceiver() {
		return usernameReceiver;
	}

	public void setUsernameReceiver(String usernameReceiver) {
		this.usernameReceiver = usernameReceiver;
	}

	public String getUsernameSender() {
		return usernameSender;
	}

	public void setUsernameSender(String usernameSender) {
		this.usernameSender = usernameSender;
	}

}
