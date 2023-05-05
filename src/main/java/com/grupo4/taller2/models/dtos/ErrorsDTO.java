package com.grupo4.taller2.models.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ErrorsDTO {
	private String field;
	private List<String> messages;
	
	public ErrorsDTO(String field) {
		super();
		this.field = field;
		this.messages = new ArrayList<>();
	}
}
