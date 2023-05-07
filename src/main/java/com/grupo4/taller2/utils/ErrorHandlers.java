package com.grupo4.taller2.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.grupo4.taller2.models.dtos.ErrorsDTO;
import com.grupo4.taller2.models.dtos.NewErrorsDTO;


@Component
public class ErrorHandlers {

	public Map<String, ErrorsDTO> mapErrors(List<FieldError> errors) {
    	Map<String, ErrorsDTO> errorsMap = new HashMap<>();
    	
    	errors.stream()
    		.forEach(error -> {
    			//Logica para separar los campos y agruparlos
    			ErrorsDTO data = errorsMap.get(error.getField()+"_errors");
    			if(data == null) {
    				data = new ErrorsDTO(error.getField());
    			}
    			
    			data.getMessages().add(error.getDefaultMessage());
    			errorsMap.put(error.getField()+"_errors", data);
    		});
    	
    	return errorsMap; 
	}
	
	public NewErrorsDTO createErrors(String field, String msg) {
    	Map<String, ErrorsDTO> errorsMap = new HashMap<>();
    	
    	return new NewErrorsDTO(field, msg);
    	
	}
}
