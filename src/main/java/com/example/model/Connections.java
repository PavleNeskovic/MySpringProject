package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connection")
public class Connections {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User friendSender;
	@ManyToOne
	private User friendReceiver;
		
	public Connections() {
		super();
	}

	public Connections(Long id, User friendSender, User friendReceiver) {
		super();
		this.id = id;
		this.friendSender = friendSender;
		this.friendReceiver = friendReceiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFriendSender() {
		return friendSender;
	}

	public void setFriendSender(User friendSender) {
		this.friendSender = friendSender;
	}

	public User getFriendReceiver() {
		return friendReceiver;
	}

	public void setFriendReceiver(User friendReceiver) {
		this.friendReceiver = friendReceiver;
	}	
}
