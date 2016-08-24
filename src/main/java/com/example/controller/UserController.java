package com.example.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserCreateForm;
import com.example.model.CurrentUser;
import com.example.model.User;
import com.example.model.UserDetails;
import com.example.service.UserService;
import com.example.validator.UserCreateFormValidator;




@RestController
public class UserController {

	  private final UserService userService;
	  private final UserCreateFormValidator userCreateFormValidator;
	    @Autowired
	    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
	        this.userService = userService;
	        this.userCreateFormValidator = userCreateFormValidator;	        
	    }
	    
	    @InitBinder("form")
	    public void initBinder(WebDataBinder binder) {
	        binder.addValidators(userCreateFormValidator);
	    }
	    
//	    @PreAuthorize("#id == userId")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    @RequestMapping(
	    		value = "/contact/byid/{id}",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<User> getUserById(
	    		@PathVariable Long id){
	    	User user = userService.getUserById(id);
	    	if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
	    	return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	    
	    @PreAuthorize("#email == authentication.name || hasAuthority('ROLE_ADMIN')")
	    @RequestMapping(
	    		value = "/contact/byemail/{email}",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<User> getUserByEmail(
	    		@PathVariable String email){
	    	User user = userService.getUserByEmail(email).get();	    
	    	if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
	    	return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	    
	    @RequestMapping(
	    		value = "/create",
	            method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<UserCreateForm> getUserCreatePage(){
	    	return new ResponseEntity<UserCreateForm>(new UserCreateForm(), HttpStatus.OK);
	}
	    
	    @RequestMapping(
				value = "/create",
				method = RequestMethod.POST,
				produces = MediaType.APPLICATION_JSON_VALUE,
				consumes = MediaType.APPLICATION_JSON_VALUE
				)
		public String handleUserCreateForm(
				//Fix @ModelAttribute("form") 
				@Valid  @RequestBody
				UserCreateForm form, 
				BindingResult bindingResult){
	    	 if (bindingResult.hasErrors()) {
	    		 System.err.println("Binding Result has errors.");
	    		 for (ObjectError objectError: bindingResult.getAllErrors()){
	    			 System.out.println(objectError);
	    		 }
	             return "user_create";
	         }
	         try {
	             userService.create(form);
	         } catch (DataIntegrityViolationException e) {
	             bindingResult.reject("email.exists", "Email already exists");
	             System.err.println("bindingResult email exists.");
	             return "user_create";
	         }
	         return "redirect:/users";
		}


}
