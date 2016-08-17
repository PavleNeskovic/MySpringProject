package com.example.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.portlet.ModelAndView;

import com.example.dto.UserCreateForm;

@org.springframework.stereotype.Controller
public class LoginController {

	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
	        return new ModelAndView("login", "error", error);
	    }
	
//	@RequestMapping(
//    		value = "/login",
//            method = RequestMethod.GET)
//    public ResponseEntity<UserCreateForm> getUserCreatePage(){
//    	return new ResponseEntity<UserCreateForm>(new UserCreateForm(), HttpStatus.CREATED);
//}
	
}
