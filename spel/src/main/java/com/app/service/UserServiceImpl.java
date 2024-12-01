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
	public boolean registerUser(UserEntity user) {
		try {
			user.setPassword(paswordEncoder.encode(user.getPassword()));
			userRepo.save(user);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	

}
