package com.grupo4.taller2.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.taller2.models.dtos.ErrorsDTO;
import com.grupo4.taller2.models.dtos.LoginDTO;
import com.grupo4.taller2.models.dtos.LoginResponseDTO;
import com.grupo4.taller2.models.dtos.RegisterDTO;
import com.grupo4.taller2.services.UserService;
import com.grupo4.taller2.utils.ErrorHandlers;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/company/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private ErrorHandlers handler;
	
	@PostMapping("/signin")
	public ResponseEntity<?> register(@Valid LoginDTO userInfo, BindingResult validations){
		if(validations.hasErrors()) {
			Map<String, ErrorsDTO> errors = handler.mapErrors(validations.getFieldErrors());
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		LoginResponseDTO res = userService.login(userInfo);
    	
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
}
