package com.grupo4.taller2.models.dtos;

import lombok.Data;

@Data
public class ChangePasswordDTO {
	private String newPassword;
	private String oldPassword;
	private String identifier;
}
