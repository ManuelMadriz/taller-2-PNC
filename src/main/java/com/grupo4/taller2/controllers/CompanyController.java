package com.grupo4.taller2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.taller2.models.dtos.ChangePasswordDTO;
import com.grupo4.taller2.models.dtos.ErrorsDTO;
import com.grupo4.taller2.models.dtos.RegisterDTO;
import com.grupo4.taller2.models.entities.User;
import com.grupo4.taller2.services.UserService;
import com.grupo4.taller2.utils.ErrorHandlers;

import jakarta.validation.Valid;


@RestController()
@RequestMapping("/company/user")
@CrossOrigin("*")
public class CompanyController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ErrorHandlers handler;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		
		List<User> usersFound = userService.findAll();
		
		return new ResponseEntity<>(usersFound, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable String id){
				
		User userFound = userService.findOneById(id);
		if(userFound == null) {
			System.out.println("Nulo");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		System.out.println("Correcto");
		return new ResponseEntity<>(userFound, HttpStatus.OK);
		}
	
	@PostMapping("/")
	public ResponseEntity<?> register(@Valid RegisterDTO userInfo, BindingResult validations){
		if(validations.hasErrors()) {
			Map<String, ErrorsDTO> errors = handler.mapErrors(validations.getFieldErrors());
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		Boolean userExists = userService.userExists(userInfo.getEmail());
		
		if(userExists == false) {
			userExists = userService.userExists(userInfo.getUsername());
			
			if(userExists == false) {
				userService.register(userInfo);
				return new ResponseEntity<>(HttpStatus.OK);}
		}
			
		return new ResponseEntity<>(handler.createErrors("identifier", "User already exists"), HttpStatus.CONFLICT);
	}
	
	@PatchMapping("/change-password")
	public ResponseEntity<?> changePasssword(@Valid ChangePasswordDTO passInfo, BindingResult validations){
		if(validations.hasErrors()) {
			Map<String, ErrorsDTO> errors = handler.mapErrors(validations.getFieldErrors());
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		Boolean userExists = userService.userExists(passInfo.getIdentifier());
		
		if(userExists == false) {
			return new ResponseEntity<>(handler.createErrors("identifier", "User does not exists"), HttpStatus.BAD_REQUEST);}
		
		userService.changePassword(passInfo);
    	
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PatchMapping("/toggle-active")
	public ResponseEntity<?> toggleActive(String identifier){
		
		if(identifier == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Boolean userExists = userService.userExists(identifier);
		
		if(userExists == false) {
			return new ResponseEntity<>(handler.createErrors("identifier", "User does not exists"),HttpStatus.BAD_REQUEST);}
		
		userService.toggleState(identifier);
    	
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
