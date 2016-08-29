package com.example.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MessageDto;
import com.example.model.Connections;
import com.example.service.ConnectionsService;

@RestController
public class ConnectionsController {
	
	private ConnectionsService connectionsService;

	public ConnectionsController(ConnectionsService connectionsService) {
		this.connectionsService = connectionsService;
	}
	
	@RequestMapping(
    		value = "/connections",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Connections> getConnectionsCreatePage(){
    	return new ResponseEntity<Connections>(new Connections(), HttpStatus.OK);
}
	
	@RequestMapping(
			value = "/connections/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Collection<Connections>> getAllConnections() {
		Collection<Connections> connections = connectionsService.getAll();
		return new ResponseEntity<Collection<Connections>>(connections, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/connections",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> createConnection(@RequestBody Connections newConnection) {
		return new ResponseEntity<>(connectionsService.create(newConnection), HttpStatus.CREATED);
	}
	
}
