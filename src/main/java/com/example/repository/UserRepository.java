package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public User findByUsernameAndPassword(String username, String password);
	//public User findOneByUsernameAndPassword(String username, String password);
	public User findOneByEmail(String email);
}
