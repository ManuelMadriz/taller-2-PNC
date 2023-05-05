package com.grupo4.taller2.services;
import java.util.List;

import com.grupo4.taller2.models.dtos.ChangePasswordDTO;
import com.grupo4.taller2.models.dtos.LoginDTO;
import com.grupo4.taller2.models.dtos.RegisterDTO;
import com.grupo4.taller2.models.entities.User;

public interface UserService {
	void login(LoginDTO log);
	void register(RegisterDTO reg);
	List<User> findAll();
	User findOneById(String identifier);
	void toggleState(String identifier);
	void changePassword(ChangePasswordDTO changePass);
}