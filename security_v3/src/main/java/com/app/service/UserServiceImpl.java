package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entities.UserEntity;
import com.app.repository.UserEntityRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserEntityRepository userRepo;
	@Autowired
	private PasswordEncoder paswordEncoder; 

	@Override
	public String registerUser(UserEntity user) {
		try {
			user.setPassword(paswordEncoder.encode(user.getPassword()));
			userRepo.save(user);
			return "Registeration Successful";
			
		} catch (Exception e) {
			return "Registration Failed";
		}
	}
	

}
