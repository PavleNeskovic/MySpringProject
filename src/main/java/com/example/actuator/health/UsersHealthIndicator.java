package com.example.actuator.health;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.example.model.User;
import com.example.service.UserService;

@Component
public class UsersHealthIndicator implements HealthIndicator {

	@Autowired
	private UserService userService;
	
	@Override
	public Health health() {
		Collection<User> users = userService.getAllUsers();
		
		//Ako se ne podize server uspesno, mozda je prazna baza, pa ulazi u if
//		if (users == null || users.size() == 0) {
//			System.err.println("Server is down, because there is no users in db.");
//			return Health.down().withDetail("count", users.size()).build();
//		}
		return Health.up().withDetail("count",users.size()).build();
	}

}
