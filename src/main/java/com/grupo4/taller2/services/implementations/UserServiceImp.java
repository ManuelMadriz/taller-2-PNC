package com.grupo4.taller2.services.implementations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.grupo4.taller2.models.dtos.ChangePasswordDTO;
import com.grupo4.taller2.models.dtos.LoginDTO;
import com.grupo4.taller2.models.dtos.LoginResponseDTO;
import com.grupo4.taller2.models.dtos.RegisterDTO;
import com.grupo4.taller2.models.entities.User;
import com.grupo4.taller2.services.UserService;

@Service
public class UserServiceImp implements UserService{
	
	private static List<User> users = new ArrayList<>();
	//para poder parsear la fecha
	private static DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	
	static {
		try {
			users.add(new User("manu", "manuel@gmail.com", "Manuel Madriz", "admin", date.parse("04/05/2023"), true, "ola12345678encriptado"));
			users.add(new User("moisacan", "moisa@gmail.com", "Mario Moisa", "user", date.parse("03/05/2023"), true, "ola12345678encriptado"));
			users.add(new User("wilhs", "wil@gmail.com", "Wilmer Hernandez", "user", date.parse("03/05/2023"), true, "ola12345678encriptado"));
			users.add(new User("rodrick", "rodri@gmail.com", "Rodrigo Molina", "admin", date.parse("04/05/2023"), true, "ola12345678encriptado"));
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public LoginResponseDTO login(LoginDTO login) {
		User user = findOneById(login.getIdentifier());
		
		if(user.getPassword().equals(login.getPassword()+"encriptado"))
			return new LoginResponseDTO(
					user.getUsername(),
					user.getEmail(),
					user.getRol());
		
		return new LoginResponseDTO();
	}

	@Override
	public void register(RegisterDTO reg) {
		
		Date time = Calendar.getInstance().getTime();
		User newUser = new User(
				reg.getUsername(),
				reg.getEmail(),
				reg.getName(),
				reg.getRol(),
				time, //Datecontract,
				true, //Estado
				reg.getPassword() + "encriptado"
				);
		users = Stream.concat(users.stream(), Stream.of(newUser))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public User findOneById(String identifier) {
		return users.stream()
				.filter(u-> (u.getUsername().equals(identifier) || u.getEmail().equals(identifier)))
				.findAny() //devuelve cualquiera que encuentre
				.orElse(null); //sino devuelve nulo
	}

	@Override
	public void toggleState(String identifier) {
		User user = findOneById(identifier);
		user.setState(!user.getState());
		
	}

	@Override
	public void changePassword(ChangePasswordDTO changePass) {
		User user = findOneById(changePass.getIdentifier());
		System.out.println(user.getPassword());
		System.out.println(changePass.getOldPassword()+"encriptado");
		System.out.println(user.getPassword().equals(changePass.getOldPassword()+"encriptado"));
		if(user.getPassword().equals(changePass.getOldPassword()+"encriptado"))
			user.setPassword(changePass.getNewPassword()+"encriptado");
	}

}
