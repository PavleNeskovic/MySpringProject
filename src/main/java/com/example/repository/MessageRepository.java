package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Message;
import com.example.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	public Collection<Message> findBySenderAndRecever(User sender, User recever);
}
