package com.example.service;

import java.util.Collection;
import java.util.Optional;

import com.example.dto.UserCreateForm;
import com.example.model.User;

public interface UserServiceInterface {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
}
