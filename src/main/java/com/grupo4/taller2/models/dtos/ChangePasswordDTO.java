package com.grupo4.taller2.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordDTO {
	@NotEmpty(message = "new password must not be empty")
	@Size(min = 8)
	private String newPassword;
	@NotEmpty(message = "old password must not be empty")
	private String oldPassword;
	@NotEmpty(message = "identifier must not be empty")
	private String identifier;
}
