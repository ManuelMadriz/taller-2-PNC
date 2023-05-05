package com.grupo4.taller2.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
	@NotEmpty(message = "username must not be empty")
	private String username;
	@NotEmpty(message = "email must not be empty")
	@Email
	private String email;
	private String name;
	@NotEmpty(message = "rol must not be empty")
	private String rol;
	@NotEmpty(message = "password must not be empty")
	@Size(min = 8)
	private String password;
}
