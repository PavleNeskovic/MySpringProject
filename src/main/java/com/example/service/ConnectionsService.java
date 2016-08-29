package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Connections;
import com.example.repository.ConnectionsRepository;
import com.example.repository.UserRepository;

@Service
public class ConnectionsService {
	@Autowired
	private ConnectionsRepository connectionsRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Collection<Connections> getAll() {
		Collection<Connections> connections = connectionsRepository.findAll();
		return connections;
	}
	
	public Connections create(Connections newConnection) {
		Connections connection = new Connections();
		connection.setFriendSender(userRepository.findOneByEmail(newConnection.getFriendSender().getEmail()).get());
		connection.setFriendReceiver(userRepository.findOneByEmail(newConnection.getFriendReceiver().getEmail()).get());
		return connectionsRepository.save(connection);
	}
	
}
