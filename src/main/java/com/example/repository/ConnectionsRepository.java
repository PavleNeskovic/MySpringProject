package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Connections;

public interface ConnectionsRepository extends JpaRepository<Connections, Long> {
	
}
