package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
    public String getHomePage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
        return "Hello " + name;
    }
}
