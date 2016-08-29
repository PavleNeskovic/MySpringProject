package com.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dto.MessageDto;
import com.example.model.Message;
import com.example.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	@Query(value = "select m from Message m where m.sender = ?1 and m.recever = ?2 or m.sender = ?2 and m.recever = ?1")
	public Collection<Message> findByCustomQuery(User sender, User recever);
}
