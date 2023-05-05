package com.grupo4.taller2.models.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class RegisterDTO {
	private String username;
	private String email;
	private String name;
	private String rol;
	private String password;
}