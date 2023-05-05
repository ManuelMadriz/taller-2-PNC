package com.grupo4.taller2.models.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String username;
	private String email;
	private String name;
	private String rol;
	private Date dateContract;
	private Boolean state;
	@JsonIgnore
	private String password;
}
