package com.grupo4.taller2.services;
import java.util.List;

import com.grupo4.taller2.models.dtos.ChangePasswordDTO;
import com.grupo4.taller2.models.dtos.LoginDTO;
import com.grupo4.taller2.models.dtos.LoginResponseDTO;
import com.grupo4.taller2.models.dtos.RegisterDTO;
import com.grupo4.taller2.models.entities.User;

public interface UserService {
	LoginResponseDTO login(LoginDTO log);
	void register(RegisterDTO reg);
	List<User> findAll();
	Boolean userExists(String identifier);
	User findOneById(String identifier);
	void toggleState(String identifier);
	void changePassword(ChangePasswordDTO changePass);
}