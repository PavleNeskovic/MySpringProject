package com.example.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.UserCreateForm;
import com.example.dto.UserDisplayData;
import com.example.model.User;

public interface UserServiceInterface {

    User getUserById(long id);
    
    User getUserByUsername(String username);

    Optional<UserDisplayData> getUserByEmail(String email);
    
    Optional<UserDisplayData> getUserDisplayDataByUsername(String name);

    Collection<UserDisplayData> getAllUsers();

    User create(UserCreateForm form);
    
    User createAdmin(UserCreateForm form);
    
    Page<UserDisplayData> listAllByPage(Pageable pageable);

//	User getByEmail(String email);
}
