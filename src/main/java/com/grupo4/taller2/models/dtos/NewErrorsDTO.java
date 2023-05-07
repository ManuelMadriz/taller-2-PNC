package com.grupo4.taller2.models.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewErrorsDTO {
	private String field;
	private String messages;
}
