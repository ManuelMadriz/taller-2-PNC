package com.grupo4.taller2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.taller2.models.dtos.ChangePasswordDTO;
import com.grupo4.taller2.models.dtos.RegisterDTO;
import com.grupo4.taller2.models.entities.User;
import com.grupo4.taller2.services.UserService;

import jakarta.validation.Valid;


@RestController()
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/all")
	public ResponseEntity<?> findAll(){
		
		List<User> usersFound = userService.findAll();
		
		return new ResponseEntity<>(usersFound, HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> register(@Valid RegisterDTO userInfo, BindingResult validations){
		if(validations.hasErrors()) {
			//String msg =validations.getAllErrors().get(0).getDefaultMessage();
			//System.out.println(msg);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		userService.register(userInfo);
    	
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PatchMapping("/user/change-password")
	public ResponseEntity<?> changePasssword(@Valid ChangePasswordDTO passInfo, BindingResult validations){
		if(validations.hasErrors()) {
			//String msg =validations.getAllErrors().get(0).getDefaultMessage();
			//System.out.println(msg);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		userService.changePassword(passInfo);
    	
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PatchMapping("/user/toggle-active")
	public ResponseEntity<?> toggleActive(String identifier){
		
		if(identifier == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		userService.toggleState(identifier);
    	
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
