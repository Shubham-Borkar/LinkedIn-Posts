package com.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.entities.UserEntity;
import com.app.jwt_utils.JwtUtils;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserAuthController {

	@Autowired
	private AuthenticationManager mgr;
	@Autowired
	private JwtUtils utils;

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody SigninRequest request) {
		Authentication principal = mgr
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		String jwtToken = utils.generateJwtToken(principal);
		return ResponseEntity.ok(new SigninResponse(jwtToken, "User authentication success!!!"));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserEntity user) {
		if (user.getRole().name().equals("ROLE_USER")) {
			return ResponseEntity.ok(userService.registerUser(user));
		} else {
			return ResponseEntity.badRequest().body("Error in Registration (Role)");
		}

	}
}
