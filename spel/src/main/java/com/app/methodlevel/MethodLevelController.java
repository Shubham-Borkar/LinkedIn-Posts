package com.app.methodlevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.UserEntity;
import com.app.repository.UserEntityRepository;

import jakarta.annotation.security.RolesAllowed;

//@RestController
@RequestMapping("/auth/ml")
@CrossOrigin("*")
public class MethodLevelController {

	@Autowired
	private UserEntityRepository userRepo;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/pre-authorize/1")
	public String createProject() {
		// Only ADMIN role can create projects
		return " @PreAuthorize(\"hasRole('ADMIN')\")";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'PROJECT_MANAGER')")
	@GetMapping("/pre-authorize/2")
	public String updateProject() {
		return "@PreAuthorize(\"hasAnyRole('ADMIN', 'PROJECT_MANAGER')\")";
	}

	@PreAuthorize("hasRole('VIEWER') or hasRole('DEVELOPER')")
	@GetMapping("/pre-authorize/3")
	public String viewProjects() {
		// VIEWER and DEVELOPER roles can view projects
		return "@PreAuthorize(\"hasRole('VIEWER') or hasRole('DEVELOPER')\")";
	}

	@PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
	@GetMapping("/private-data/pre/{id}")
	public UserEntity getUserInfo(@PathVariable Long id) {
		// get Principle object to verify behavior
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.err.println("Email,user id of Authenticate User -> " + ((UserEntity) principal).getEmail() + ", "
				+ ((UserEntity) principal).getId());
		// Find and return the user info if belong to user or an admin
		UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		System.err.println("Return Object -> " + userEntity);
		return userEntity;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostAuthorize("returnObject.email == authentication.principal.email or hasRole('ADMIN')")
	@GetMapping("/private-data/post/{id}")
	public UserEntity getUserEmailAddress(@PathVariable Long id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.err.println("Email,user id of Authenticate User -> " + ((UserEntity) principal).getEmail() + ", "
				+ ((UserEntity) principal).getId());
		// Find and return the user info if belong to user or an admin
		UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		System.err.println("Return Object -> " + userEntity);
		return userEntity;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/admin-only/secured")
	public String adminOnlyMethodSecured() {
		return "Admin Only using @Secured - spring security annotation";
	}

	@RolesAllowed({ "ADMIN" })
	@GetMapping("/admin-only/rolesAllowed")
	public String adminOnlyMethodRolesAllowed() {
		return "Admin Only using @RolesAllowed - java EE standard jakarta package";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
