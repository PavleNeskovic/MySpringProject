package com.example.service;


import org.springframework.stereotype.Service;

import com.example.model.CurrentUser;
import com.example.model.Role;

@Service
public class CurrentUserService {
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ROLE_ADMIN || currentUser.getId().equals(userId));
    }

}
