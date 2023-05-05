package com.grupo4.taller2.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
	@NotEmpty(message = "password must not be empty")
	private String password;
	@NotEmpty(message = "identifier must not be empty")
	private String identifier;
}
