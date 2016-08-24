package com.example.service;

import java.util.Collection;
import java.util.Optional;

import com.example.dto.UserCreateForm;
import com.example.dto.UserDisplayData;
import com.example.model.User;

public interface UserServiceInterface {

    User getUserById(long id);

    Optional<UserDisplayData> getUserByEmail(String email);

    Collection<UserDisplayData> getAllUsers();

    User create(UserCreateForm form);
    
    User createAdmin(UserCreateForm form);

//	User getByEmail(String email);
}
